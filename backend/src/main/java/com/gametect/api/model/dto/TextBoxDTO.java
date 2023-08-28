package com.gametect.api.model.dto;

public class TextBoxDTO {
    private Integer id;
   
    private Integer subsectionId;

    private String text; 

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
