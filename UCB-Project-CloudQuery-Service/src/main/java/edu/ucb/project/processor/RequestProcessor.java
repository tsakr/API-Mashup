package edu.ucb.project.processor;

import java.util.logging.Logger;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import edu.ucb.project.model.Query;

/**
 * @author Tamer Sakr (tamer.sakr@ucsf.edu)
 * 
 */
public class RequestProcessor implements Processor {
	private static final Logger LOG = Logger.getLogger(RequestProcessor.class.getPackage().getName());

	@Override
	public void process(Exchange exchange) throws Exception {

		Query inPayload = exchange.getIn().getBody(Query.class);
		String queryString = "q=" + inPayload.getCity() + "+" + inPayload.getState();

		exchange.getIn().setHeader(Exchange.HTTP_QUERY, queryString);
	}
}
