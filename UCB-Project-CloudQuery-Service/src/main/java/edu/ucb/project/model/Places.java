package edu.ucb.project.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Tamer Sakr (tamer.sakr@ucsf.edu)
 * 
 */
@XmlRootElement(name = "places")
public class Places {

	private String place;
	private String woeid;

	public Places() {
	}

	public void setWoeid(String woeid) {
		this.woeid = woeid;
	}

	public String getWoeid() {
		return woeid;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	@Override
	public String toString() {
		return "Places [" + (place != null ? "place=" + place + ", " : "")
				+ (woeid != null ? "woeid=" + woeid : "") + "]";
	}

}