package edu.ucb.project.model;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author Tamer Sakr (tamer.sakr@ucsf.edu)
 * 
 */
@XStreamAlias("query")
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
}
