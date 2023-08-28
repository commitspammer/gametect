package com.gametect.api.model;

import jakarta.persistence.Id;

public class SelectionBox {
    @Id
    private Integer id;

    private Integer projectId;
    
    private Integer subsectionId;

    private String question;

    public Integer getId() {
        return id;
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

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
    
}
