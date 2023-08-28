package com.gametect.api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Id;

public class Section {

	@Id
	private Integer id;
	private String title;
	private String description;
	private Integer frameworkId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getFrameworkId() {
		return frameworkId;
	}

	public void setFrameworkId(Integer frameworkId) {
		this.frameworkId = frameworkId;
	}

}
