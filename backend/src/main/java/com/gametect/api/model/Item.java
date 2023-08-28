package com.gametect.api.model;

public class Item {
    private Integer selectionBoxId;

    private String description;

    private Boolean selected;

    public Integer getSelectionBoxId() {
        return selectionBoxId;
    }

    public void setSelectionBoxId(Integer selectionBoxId) {
        this.selectionBoxId = selectionBoxId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
     
}
