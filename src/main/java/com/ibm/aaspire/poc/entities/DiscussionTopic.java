package com.ibm.aaspire.poc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DISCUSSIONTOPIC")
public class DiscussionTopic {

	public DiscussionTopic() {
	}

	public DiscussionTopic(String id, String description, String longDescription) {
		this.id = id;
		this.description = description;
		this.longDescription = longDescription;
	}

	@Id
	private String id;
	
	private String description;

	@Column(name = "long_description")
	private String longDescription;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}
}
