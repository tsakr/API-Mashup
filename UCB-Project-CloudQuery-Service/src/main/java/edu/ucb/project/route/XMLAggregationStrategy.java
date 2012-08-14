package edu.ucb.project.route;

import java.util.logging.Logger;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

/**
 * 
 * @author Tamer Sakr (tamer.sakr@ucsf.edu)
 *
 */
public class XMLAggregationStrategy implements AggregationStrategy {
	
	private static final Logger LOG = Logger.getLogger(XMLAggregationStrategy.class.getPackage().getName());

	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		
		if (oldExchange == null) {
			return newExchange;
		}
		
		String oldBody = oldExchange.getIn().getBody(String.class);
		String newBody = newExchange.getIn().getBody(String.class);
		String body = oldBody + newBody;

		oldExchange.getIn().setBody(body);
		
		LOG.info("******************** XMLAggregationStrategy RESPONSE: " + body);
		
		return oldExchange;
	}
}
