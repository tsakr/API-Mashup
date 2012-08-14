package edu.ucb.project.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Tamer.Sakr@ucsf.edu
 * 
 * Accepts XML payload of this format:
 *	
 *   <query>
 *   	<city>Berkeley</city>
 *   	<state>CA</state>
 *   	<operation>processQuery</operation>
 *   </query>
 *		
 */
@XmlRootElement(name = "query")
@XmlAccessorType(XmlAccessType.FIELD)
public class AuditDocument {
	@XmlElement
	private String city;
	@XmlElement
	private String state;
	@XmlElement
	private String time;
	@XmlElement
	private String operation;

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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getOperation() {
		return operation;
	}

	@Override
	public String toString() {
		return "AuditDocument [" + (city != null ? "city=" + city + ", " : "")
				+ (state != null ? "state=" + state + ", " : "")
				+ (time != null ? "time=" + time : "") + "]";
	}

}
