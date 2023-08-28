package com.gametect.api.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class FrameworkDTO {

	private Integer id;
	private String name;
	private String tutorial;
	private List<SectionDTO> sections;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTutorial() {
		return tutorial;
	}

	public void setTutorial(String tutorial) {
		this.tutorial = tutorial;
	}

	public List<SectionDTO> getSections() {
		return sections;
	}

	public void setSections(List<SectionDTO> sections) {
		this.sections = sections;
	}

}
