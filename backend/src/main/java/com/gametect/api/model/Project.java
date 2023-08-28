package com.gametect.api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Id;

public class Project {

    @Id
	private Integer id;

	private String title;

	private String banner;

	private String description;

	private Boolean archived;

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

	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getArchived() {
		return archived;
	}

	public void setArchived(Boolean archived) {
		this.archived = archived;
	}

	public Integer getFrameworkId() {
		return frameworkId;
	}

	public void setFrameworkId(Integer frameworkId) {
		this.frameworkId = frameworkId;
	}
}
