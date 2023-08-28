package com.gametect.api.model.dto;

import java.util.List;

public class ProjectToolsDTO {
    private Integer id;
    
	private String title;

	private String description;

    private List<CharacterSheetDTO> characterSheets;

    private List<SelectionBoxDTO> selectionBoxes;

    private List<TextBoxDTO> textBoxes;

    private List<ImageDTO> images;

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

    public List<CharacterSheetDTO> getCharacterSheets() {
        return characterSheets;
    }
    public void setCharacterSheets(List<CharacterSheetDTO> characterSheets) {
        this.characterSheets = characterSheets;
    }

    public List<SelectionBoxDTO> getSelectionBoxes() {
        return selectionBoxes;
    }
    public void setSelectionBoxes(List<SelectionBoxDTO> selectionBoxes) {
        this.selectionBoxes = selectionBoxes;
    }

    public List<TextBoxDTO> getTextBoxes() {
        return textBoxes;
    }
    public void setTextBoxes(List<TextBoxDTO> textBoxes) {
        this.textBoxes = textBoxes;
    }

    public List<ImageDTO> getImages() {
        return images;
    }
    public void setImages(List<ImageDTO> images) {
        this.images = images;
    }

}
