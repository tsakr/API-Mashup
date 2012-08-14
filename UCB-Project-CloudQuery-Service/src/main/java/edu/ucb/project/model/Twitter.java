package edu.ucb.project.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Tamer Sakr (tamer.sakr@ucsf.edu)
 * 
 */
@XmlRootElement(name = "feed")
@XmlAccessorType(XmlAccessType.FIELD)
public class Twitter {

	@XmlElement
	private String id;
	@XmlElement
	private String published;
	@XmlElement
	private String link;
	@XmlElement
	private String title;
	@XmlElement
	private String content;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPublished() {
		return published;
	}

	public void setPublished(String published) {
		this.published = published;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
