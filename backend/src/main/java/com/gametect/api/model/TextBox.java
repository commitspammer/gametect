package com.gametect.api.model;

import jakarta.persistence.Id;

public class TextBox {
    @Id
    private Integer id;

    private Integer projectId;
    
    private Integer subsectionId;

    private String text;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
}   
