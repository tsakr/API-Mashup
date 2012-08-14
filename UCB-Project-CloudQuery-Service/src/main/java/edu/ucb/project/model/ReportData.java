package edu.ucb.project.model;

import java.io.Serializable;

import org.w3c.dom.Document;

import org.apache.camel.Exchange;
import org.apache.camel.Expression;

/**
 * @author Tamer Sakr (tamer.sakr@ucsf.edu)
 * 
 */
public class ReportData implements Serializable {

	private static final long serialVersionUID = 4543538900840504684L;

	private String city;
	private String state;
	private Document weather;

	public ReportData() {
	}

	public ReportData(String city, String state) {
		this.city = city;
		this.state = state;
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

	public Document getWeather() {
		return weather;
	}

	public void setWeather(Document weather) {
		this.weather = weather;
	}

	public static Expression city() {
		return new Expression() {
			public <T> T evaluate(Exchange exchange, Class<T> type) {
				return type.cast(exchange.getIn().getBody(ReportData.class)
						.getCity());
			}
		};
	}

	public static Expression state() {
		return new Expression() {
			public <T> T evaluate(Exchange exchange, Class<T> type) {
				return type.cast(exchange.getIn().getBody(ReportData.class)
						.getState());
			}
		};
	}
}
