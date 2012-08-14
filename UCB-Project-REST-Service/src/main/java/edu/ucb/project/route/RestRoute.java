package edu.ucb.project.route;

import org.apache.camel.builder.NoErrorHandlerBuilder;
import org.apache.camel.builder.RouteBuilder;

import edu.ucb.project.processor.RequestProcessor;
import edu.ucb.project.resource.RestResource;


/**
 * @author Tamer.Sakr@ucsf.edu
 */

public class RestRoute extends RouteBuilder {
	
	private static String resourceClasses = RestResource.class.getName();
	private static final String CXFRS_UCB_RESOURCE_URI = "cxfrs://http://localhost:9000/?resourceClasses=" + resourceClasses;
	
	
	@Override
	public void configure() throws Exception {

		errorHandler(new NoErrorHandlerBuilder());

		from(CXFRS_UCB_RESOURCE_URI)
			.routeId("UCB-REST-ROUTE")
			.process(new RequestProcessor())
			.convertBodyTo(String.class)
			.inOnly("activemq:ucb.project.data", "activemq:ucb.project.data.audit");
	}
}
