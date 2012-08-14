package edu.ucb.project.route;

import java.util.logging.Logger;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;


public class XMLAggregationStrategy implements AggregationStrategy {
	
	private static final Logger LOG = Logger.getLogger(XMLAggregationStrategy.class.getPackage().getName());

	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		
		if (oldExchange == null) {
			return newExchange;
		}
		
		String oldBody = oldExchange.getIn().getBody(String.class);
		String newBody = newExchange.getIn().getBody(String.class);
		String body = oldBody + newBody;

//		body = body
//				.replaceAll("<\\?xml version=\"1\\.0\" encoding=\"UTF-8\"\\?>", "");
//				.replaceAll("</earthquakes>(.*)<earthquakes>", "")
//				.replaceAll("</earthquakes><earthquakes xmlns:geo=\"http://www\\.w3\\.org/2003/01/geo/wgs84_pos#\">", "").replaceAll("</earthquakes><earthquakes>", "");

		oldExchange.getIn().setBody(body);
		
		LOG.info("******************** XMLAggregationStrategy RESPONSE: " + body);
		
		return oldExchange;
	}
}
