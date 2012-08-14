package edu.ucb.project.processor;

import java.util.logging.Logger;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * @author Tamer Sakr (tamer.sakr@ucsf.edu)
 *
 */
public class YahooWeatherRequestProcessor implements Processor {

	private static final Logger LOG = Logger.getLogger(YahooWeatherRequestProcessor.class.getPackage().getName());

	@Override
	public void process(Exchange exchange) throws Exception {

		String inPayload = exchange.getIn().getBody(String.class);
		LOG.info("Constructing query string for WOEID = " + inPayload);
		
		String queryString = "w=" + inPayload + "&u=c";
		exchange.getIn().setHeader(Exchange.HTTP_QUERY, queryString);
	}
}
