package com.gametect.api.model;

import jakarta.persistence.Id;


public class CharacterSheet {

	@Id
    private int id;

    private Integer projectId;
    
    private Integer subsectionId;

    private String name;

    private String referenceSheet;
	
    private Double height;
    
    private Double weight;

    private String history;

    private String motivation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getSubsectionId() {
        return subsectionId;
    }

    public void setSubsectionId(Integer subsectionId) {
        this.subsectionId = subsectionId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReferenceSheet() {
        return referenceSheet;
    }

    public void setReferenceSheet(String referenceSheet) {
        this.referenceSheet = referenceSheet;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
    
    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    
}
