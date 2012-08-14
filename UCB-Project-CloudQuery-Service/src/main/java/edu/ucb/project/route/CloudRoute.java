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
			//.split(simple("true"));
		
		/* ***************************** Twitter API *************************** */
		from("file:src/twitter_data?delete=true&delay=30s")
			.routeId("UCB-CLOUD-Twitter-ROUTE")
			.setHeader("twitter", constant(true)).log("true")
			.process(new RequestProcessor())
			.to("http4://search.twitter.com/search.atom")
			.unmarshal().rss()
			.process(new ResponseProcessor())
			.setHeader("visited", constant(true)).log("true")
			.log("retrieve")
			.convertBodyTo(String.class)
			.to("direct:collectorChannel");
		
		from("direct:collectorChannel")
			.routeId("UCB-CLOUD-ROUTE_D")
       		.log("results")
       		.aggregate(header("visited"), new XMLAggregationStrategy())
			.completionSize(1).delay(3000)
			.to("direct:UnmarshallSources");
		
		from("direct:UnmarshallSources")
			.routeId("UCB-CLOUD-ROUTE_E")
       		.convertBodyTo(String.class)
       		.to("file:src/data2?noop=true").end();
	
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
			.routeId("UCB-CLOUD-Yahoo-Weather-ROUTE_A")
			.process(new YahooWeatherRequestProcessor())
			.to("http4://weather.yahooapis.com/forecastrss")
			.unmarshal().rss()
			.process(new YahooWeatherResponseProcessor())
			.setHeader("visited", constant(true)).log("true")
			.to("direct:YahooWeatherCollectorChannel");
		
		from("direct:YahooWeatherCollectorChannel")
			.routeId("UCB-CLOUD-Yahoo-Weather-ROUTE_B")
			.convertBodyTo(String.class)
			.to("file:src/data4?noop=true").end();
	/* ******************************************************** */
	}
}
