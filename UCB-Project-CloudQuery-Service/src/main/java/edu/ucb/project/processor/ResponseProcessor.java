package edu.ucb.project.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;

/*
 * Exchange Response Handler
 * 
 */
public class ResponseProcessor implements Processor {

	private static final Logger LOG = Logger.getLogger(ResponseProcessor.class.getPackage().getName());

	@SuppressWarnings("unchecked")
	public void process(Exchange exchange) throws Exception {

		Message in = exchange.getIn();

		int responseCode = in.getHeader(Exchange.HTTP_RESPONSE_CODE, Integer.class);
		LOG.info("******************** HTTP RESPONSE: " + responseCode);

		SyndFeed body = exchange.getIn().getBody(SyndFeed.class);

		String value = null;
		List<SyndEntryImpl> entry = (List<SyndEntryImpl>) body.getEntries();
		List<String> feedTitles = new ArrayList<String>();

		for (int i = 0; i < body.getEntries().size(); i++) {
			value = entry.get(i).getTitleEx().getValue();
			feedTitles.add(value);
		}
		
		exchange.getIn().setBody(feedTitles);
	}
}
