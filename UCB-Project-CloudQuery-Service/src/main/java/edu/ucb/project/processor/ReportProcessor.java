package edu.ucb.project.processor;

import java.util.logging.Logger;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import edu.ucb.project.model.ReportData;
/**
 * 
 * @author Tamer Sakr (tamer.sakr@ucsf.edu)
 *
 */
public class ReportProcessor implements Processor {
	
	private static final Logger LOG = Logger.getLogger(ReportProcessor.class.getPackage().getName());

    public void process(Exchange exchange) throws Exception {
    	LOG.info("**************************************************");
    	
        ReportData data = exchange.getIn().getBody(ReportData.class);
        
        XPathFactory xpfactory = XPathFactory.newInstance();
        XPath xpath = xpfactory.newXPath();

        // Extract result values via XPath
        String city = xpath.evaluate("//forecast_information/city/@data", data.getWeather());
        String cond = xpath.evaluate("//current_conditions/condition/@data", data.getWeather());
        String temp = xpath.evaluate("//current_conditions/temp_c/@data", data.getWeather());
        
        if (city == null || city.length() == 0) {
            city = data.getCity();
            cond = "<error retrieving current condition>";
            temp = "<error retrieving current temperature>";
        }
        
        exchange.getIn().setBody(new StringBuilder()
            .append("\n").append("Weather report for:  ").append(city)
            .append("\n").append("Current condition:   ").append(cond)
            .append("\n").append("Current temperature: ").append(temp).append(" (Celsius)").toString());
    }

}
