package edu.ucb.project.processor;

import java.util.List;
import java.util.logging.Logger;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.component.cxf.common.message.CxfConstants;
import org.apache.cxf.message.MessageContentsList;

import com.thoughtworks.xstream.XStream;

import edu.ucb.project.model.Query;

/**
 * @author Tamer Sakr (tamer.sakr@ucsf.edu)
 * 
 */
public class RequestProcessor implements Processor {
	private static final Logger LOG = Logger.getLogger(RequestProcessor.class.getPackage().getName());

	@Override
	public void process(Exchange exchange) throws Exception {

		Message inMessage = exchange.getIn();

		Query query = null;
		List<?> inPayload = null;

		String httpMethod = inMessage.getHeader(Exchange.HTTP_METHOD, String.class);
		String operationName = inMessage.getHeader(CxfConstants.OPERATION_NAME, String.class);

		LOG.info("httpMethod = " + httpMethod);
		LOG.info("operationName = " + operationName);

		String uri = inMessage.getHeader(Exchange.HTTP_URI, String.class);
		LOG.info("uri = " + uri);

		if ("GET".equals(httpMethod) && "processQuery".equals(operationName)) {
			inPayload = inMessage.getBody(MessageContentsList.class);

			if (!inPayload.isEmpty()) {
				query = new Query();
				String[] query_tokens = inPayload.get(0).toString().split("\\.");
				query.setCity(query_tokens[0]);
				query.setState(query_tokens[1]);
				query.setOperation(operationName);
			}
		}else if ("GET".equals(httpMethod) && "getCityState".equals(operationName)) {
			inPayload = inMessage.getBody(MessageContentsList.class);
			
			if (!inPayload.isEmpty()) {
				query = new Query();
				query.setCity(inPayload.get(0).toString().toLowerCase());
				query.setState(inPayload.get(1).toString().toLowerCase());
				query.setOperation(operationName);
			}
		} else {
			LOG.warning("REST Operation not Found!");
		}

		XStream xstream = new XStream();
		xstream.processAnnotations(Query.class);
		String queryXML = xstream.toXML(query);

		// return XML payload of this format:
		/*
		 * <query> 
		 * 	<city>Berkeley</city> 
		 * 	<state>CA</state>
		 * 	<operation>processQuery</operation> 
		 * </query>
		 */

		exchange.getIn().setHeader(Exchange.CONTENT_TYPE, "application/xml");
		exchange.getIn().setBody(queryXML);
	}
}
