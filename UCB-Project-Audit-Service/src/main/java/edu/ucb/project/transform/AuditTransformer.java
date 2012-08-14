package edu.ucb.project.transform;

import org.apache.camel.Converter;
import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.jpa.JpaTemplate;

import edu.ucb.project.jpa.AuditEntity;
import edu.ucb.project.model.AuditDocument;

/**
 * A Message Transformer of an XML Query document to an Audit entity bean
 * 
 * @author Tamer.Sakr@ucsf.edu
 */
@Converter
public class AuditTransformer {

	private static final transient Logger LOG = LoggerFactory.getLogger(AuditTransformer.class);

	/**
	 * A transformation method to convert an XML Query document to an Audit entity bean
	 * @throws Exception
	 */
	@Converter
	public AuditEntity toCustomer(AuditDocument doc, Exchange exchange) throws Exception {
		//get template from Exchange header for additional DB search...
		JpaTemplate template = exchange.getIn().getHeader("CamelJpaTemplate", JpaTemplate.class);

		AuditEntity entity = new AuditEntity();
		entity.setCity(doc.getCity());
		entity.setState(doc.getState());
		entity.setOperation(doc.getOperation());
		
		LOG.debug("Created object AuditEntity: " + entity);
		
		return entity;
	}
}
