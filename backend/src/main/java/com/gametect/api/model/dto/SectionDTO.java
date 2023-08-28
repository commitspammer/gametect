package com.gametect.api.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SectionDTO {

	private Integer id;
	private String title;
	private String description;
	private List<SubsectionDTO> subsections;

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

	public List<SubsectionDTO> getSubsections() {
		return subsections;
	}

	public void setSubsections(List<SubsectionDTO> subsections) {
		this.subsections = subsections;
	}

}
