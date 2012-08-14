package edu.ucb.project.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Tamer Sakr (tamer.sakr@ucsf.edu)
 * 
 *         Binds to XML payload of this format:
 * 
 *         <?xml version="1.0" encoding="UTF-8"?> 
 *         <query> 
 *         		<city>Berkeley</city>
 *         		<state>CA</state> 
 *         		<operation>processQuery</operation> 
 *         </query>
 * 
 */
@XmlRootElement(name = "query")
@XmlType(propOrder = { "city", "state", "operation" })
public class Query implements Serializable {

	private static final long serialVersionUID = -5691308314411035L;
	private String city;
	private String state;
	private String operation;

	public Query() {
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

	@Override
	public String toString() {
		return "Query [" + (city != null ? "city=" + city + ", " : "")
				+ (state != null ? "state=" + state + ", " : "")
				+ (operation != null ? "operation=" + operation : "") + "]";
	}
}
