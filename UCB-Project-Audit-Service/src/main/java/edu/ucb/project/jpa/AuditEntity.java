package edu.ucb.project.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * An entity bean which can be marshalled to/from XML
 * 
 * @author Tamer.Sakr@ucsf.edu
 */
@Entity(name = "audit_entity")
@XmlRootElement(name = "audit_entity")
@XmlAccessorType(XmlAccessType.FIELD)
public class AuditEntity {

	@XmlAttribute
	private Long id;
	private String city;
	private String state;
	private String operation;

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getOperation() {
		return operation;
	}

}
