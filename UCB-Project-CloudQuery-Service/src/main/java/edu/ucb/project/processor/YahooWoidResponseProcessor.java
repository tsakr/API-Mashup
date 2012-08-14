package edu.ucb.project.processor;

import java.io.StringReader;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * @author Tamer Sakr (tamer.sakr@ucsf.edu)
 *
 */
public class YahooWoidResponseProcessor implements Processor {
	
	private static final Logger LOG = Logger.getLogger(YahooWoidResponseProcessor.class.getPackage().getName());

	@Override
	public void process(Exchange exchange) throws Exception {
		
		String body = exchange.getIn().getBody(String.class);
		
		DocumentBuilderFactory factoryBuilder = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factoryBuilder.newDocumentBuilder();
        Document doc = builder.parse(new InputSource(new StringReader(body)));
        
        NodeList places =  doc.getChildNodes();
        String woeid = places.item(0).getFirstChild().getFirstChild().getTextContent();
        
		exchange.getIn().setBody(woeid);
	}
}
