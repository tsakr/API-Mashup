package edu.ucb.project.route;

import org.apache.camel.Exchange;
import org.apache.camel.builder.NoErrorHandlerBuilder;
import org.apache.camel.builder.RouteBuilder;
import static org.apache.camel.language.juel.JuelExpression.el;

import edu.ucb.project.model.AuditDocument;

/**
 * @author Tamer.Sakr@ucsf.edu
 */

public class AuditRoute extends RouteBuilder {

	public void configure() throws Exception {

		errorHandler(new NoErrorHandlerBuilder());
		
		from("activemq:ucb.project.data.audit")  
				.routeId("UCB-AUDIT-ROUTE")
				.convertBodyTo(AuditDocument.class)
				.to("jpa:edu.ucb.project.jpa.AuditEntity");

		// dump the database to file(s)
		from("jpa:edu.ucb.project.jpa.AuditEntity?consumeDelete=false&delay=30000&consumeLockEntity=false")
				.setHeader(Exchange.FILE_NAME, el("${in.body.city}.xml"))
				.to("file:src/audit");
	}
}