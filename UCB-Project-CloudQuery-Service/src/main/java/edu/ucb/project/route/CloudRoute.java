package edu.ucb.project.route;

import org.apache.camel.Exchange;
import org.apache.camel.builder.NoErrorHandlerBuilder;
import org.apache.camel.builder.RouteBuilder;

import edu.ucb.project.processor.RequestProcessor;
import edu.ucb.project.processor.ResponseProcessor;
import edu.ucb.project.processor.YahooWeatherRequestProcessor;
import edu.ucb.project.processor.YahooWeatherResponseProcessor;
import edu.ucb.project.processor.YahooWoidRequestProcessor;
import edu.ucb.project.processor.YahooWoidResponseProcessor;

/**
 * @author Tamer.Sakr@ucsf.edu
 */

public class CloudRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		errorHandler(new NoErrorHandlerBuilder());
		
		from("activemq:ucb.project.data")
			.routeId("UCB-CLOUD-ROUTE_A")
			.to("file:src/twitter_data?noop=true", "file:src/yahoo_data?noop=true");
		
		/* ***************************** Twitter API *************************** */
		from("file:src/twitter_data?delete=true&delay=30s")
			.routeId("UCB-CLOUD-Twitter-ROUTE_A")
			.process(new RequestProcessor())
			.to("http4://search.twitter.com/search.atom")
			.unmarshal().rss()
			.process(new ResponseProcessor())
			.setHeader("visited", constant(true)).log("true")
			.convertBodyTo(String.class)
			.to("direct:AggregationChannel");
		
	/* ************************* Yahoo API ******************************* */
		from("file:src/yahoo_data?delete=true&delay=30s")
			.routeId("UCB-CLOUD-Yahoo-Woeid-ROUTE_A")
			.process(new YahooWoidRequestProcessor());
			
		from("direct:yahoo_woeid_extractor")
			.routeId("UCB-CLOUD-Yahoo-Woeid-ROUTE_B")
			.setHeader(Exchange.HTTP_URI, body())
	        .log("Extracting content from: '${body}'")
	        .to("http4:extractor")
	        .unmarshal().tidyMarkup()
	        .log("Html from: '${body}'")
	        .to("direct:YahooWoeidCollectorChannel");
		
		from("direct:YahooWoeidCollectorChannel")
			.routeId("UCB-CLOUD-Yahoo-Woeid-ROUTE_C")
			.process(new YahooWoidResponseProcessor())
			.to("file:src/data3?noop=true");
		
		from("file:src/data3?delete=true&delay=30s")
			.routeId("UCB-CLOUD-Yahoo-Weather-ROUTE")
			.process(new YahooWeatherRequestProcessor())
			.to("http4://weather.yahooapis.com/forecastrss")
			.unmarshal().rss()
			.process(new YahooWeatherResponseProcessor())
			.setHeader("visited", constant(true)).log("true")
			.to("direct:AggregationChannel");
		
	/* ************************** Result Aggregation ****************************** */
		from("direct:AggregationChannel")
			.routeId("UCB-CLOUD-Aggregation-ROUTE")
			.aggregate(header("visited"), new XMLAggregationStrategy())
			.completionSize(2).delay(3000)
			.to("activemq:ucb.project.api.result");
		
	}
}
