package com.gametect.api.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ItemDTO {
    @JsonIgnore
    private Integer SelectionBoxId;

    private String description;

    private Boolean selected;

    public Integer getSelectionBoxId() {
        return SelectionBoxId;
    }

    public void setSelectionBoxId(Integer selectionBoxId) {
        SelectionBoxId = selectionBoxId;
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
