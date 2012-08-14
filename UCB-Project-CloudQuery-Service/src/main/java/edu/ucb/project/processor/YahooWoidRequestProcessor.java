package edu.ucb.project.processor;

import java.util.logging.Logger;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;

import edu.ucb.project.model.Query;

/**
 * @author Tamer Sakr (tamer.sakr@ucsf.edu)
 * 
 */
public class YahooWoidRequestProcessor implements Processor {
	private static final Logger LOG = Logger.getLogger(YahooWoidRequestProcessor.class.getPackage().getName());

	@Override
	public void process(Exchange exchange) throws Exception {
		
		String yahooAppId = "XLyjfQjV34GqGXwc3JRuhmmbtthHqmUeJz8oBtLLtnvN1wtQf3l.ThRxwwjeto_cPuTWvZYHbwLDCQgOsrmgkHYIHJWZtTc-";

		Query inPayload = exchange.getIn().getBody(Query.class);
		
		String q = inPayload.getCity() + "%20" + inPayload.getState() + "%20" + "usa";
		String queryString = ".q('" + q.toLowerCase() + "')?appid=" + yahooAppId;
		
		String yahooURL = "http://where.yahooapis.com/v1/places" + queryString;
	    ProducerTemplate template = exchange.getContext().createProducerTemplate();
	    String result = template.requestBody("direct:yahoo_woeid_extractor", yahooURL, String.class);
	    LOG.info("Extracted:" +  result);
	
	}
}