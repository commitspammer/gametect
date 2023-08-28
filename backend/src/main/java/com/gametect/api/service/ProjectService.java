package com.gametect.api.service;

import java.util.List;
import java.util.function.Predicate;
import java.util.Optional;

import com.gametect.api.exception.FrameworkNotFoundException;
import com.gametect.api.exception.ObrigationsFieldNullException;
import com.gametect.api.exception.ProjectNotFoundException;
import com.gametect.api.model.Framework;
import com.gametect.api.model.Project;
import com.gametect.api.model.dto.CharacterSheetDTO;
import com.gametect.api.model.dto.ImageDTO;
import com.gametect.api.model.dto.ItemDTO;
import com.gametect.api.model.dto.ProjectToolsDTO;
import com.gametect.api.model.dto.SelectionBoxDTO;
import com.gametect.api.model.dto.TextBoxDTO;
import com.gametect.api.repository.CharacterSheetRepository;
import com.gametect.api.repository.FrameworkRepository;
import com.gametect.api.repository.ImageRepository;
import com.gametect.api.repository.ItemRepository;
import com.gametect.api.repository.ProjectRepository;
import com.gametect.api.repository.SelectionBoxRepository;
import com.gametect.api.repository.TextBoxRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private FrameworkRepository frameworkRepository;

    @Autowired
    private CharacterSheetRepository characterSheetRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private SelectionBoxRepository selectionBoxRepository;

    @Autowired
    private TextBoxRepository textBoxRepository;

    @Autowired
    private ItemRepository itemRepository;

    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    public List<Project> findByUser(Integer id) {
        return projectRepository.findByUser(id);
    }

    public List<Project> getOnly(Predicate<Project> condition) {
        return projectRepository.findAll().stream().filter(condition).toList();
    }

    public Optional<Project> getOne(Predicate<Project> condition) {
        return projectRepository.findAll().stream().filter(condition).findFirst();
    }

    public Project create(Project p) {
        validateFields(p);
        return projectRepository.save(p);
    }

    public Project update(Project p) {
        validateFields(p);
        return projectRepository.update(p);
    }

    public void deleteById(Integer id) {
        getById(id);
        projectRepository.deleteById(id);
    }

    public void deleteOnly(Predicate<Project> condition) {
        projectRepository.deleteAll();
    }

    public Project getById(Integer id) {
        var optional = projectRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new ProjectNotFoundException();
        }
    }

    public Optional<ProjectToolsDTO> toolsProjectbyId(Integer id) {
        return projectRepository.findById(id)
            .map(p -> {
                ProjectToolsDTO pdto = new ProjectToolsDTO();
                pdto.setId(p.getId());
                pdto.setTitle(p.getTitle());
                pdto.setDescription(p.getDescription());
                pdto.setCharacterSheets(characterSheetRepository.findByProjectId(p.getId())
                    .stream()
                    .map(c -> {
                        CharacterSheetDTO cdto = new CharacterSheetDTO();
                        cdto.setId(c.getId());
                        cdto.setHeight(c.getHeight());
                        cdto.setWeight(c.getWeight());
                        cdto.setHistory(c.getHistory());
                        cdto.setMotivation(c.getMotivation());
                        cdto.setReferenceSheet(c.getReferenceSheet());
                        cdto.setSubsectionId(c.getSubsectionId());
                        cdto.setName(c.getName());
                        return cdto;
                    }).toList()
                );
                pdto.setImages(imageRepository.findByProjectId(p.getId())
                    .stream()
                    .map(i -> {
                        ImageDTO idto = new ImageDTO();
                        idto.setId(i.getId());
                        idto.setSubsectionId(i.getSubsectionId());
                        idto.setHeight(i.getHeight());
                        idto.setWidth(i.getWidth());
                        idto.setUrl(i.getUrl());
                        return idto;
                    }).toList()
                );
                pdto.setSelectionBoxes(selectionBoxRepository.findByProjectId(id)
                    .stream()
                    .map(s -> {
                        SelectionBoxDTO sdto = new SelectionBoxDTO();
                        sdto.setId(s.getId());
                        sdto.setProjectId(s.getProjectId());
                        sdto.setSubsectionId(s.getSubsectionId());
                        sdto.setQuestion(s.getQuestion());
                        sdto.setItems(itemRepository.findBySelectionBoxId(s.getId())
                            .stream()
                            .map(i -> {
                                ItemDTO idto = new ItemDTO();
                                idto.setDescription(i.getDescription());
                                idto.setSelected(i.getSelected());
                                idto.setSelectionBoxId(i.getSelectionBoxId());
                                return idto;
                            }).toList()
                        );
                        return sdto;
                    }).toList()
                );
                pdto.setTextBoxes(textBoxRepository.findByProjectId(id)
                    .stream()
                    .map(t -> {
                        TextBoxDTO tdto = new TextBoxDTO();
                        tdto.setId(t.getId());
                        tdto.setSubsectionId(t.getSubsectionId());
                        tdto.setText(t.getText());
                        return tdto;
                    }).toList()
                );
                return pdto;
            });
    }

    private void validateFields(Project p) {
        if (p.getBanner() == null || p.getBanner().isEmpty()) {
            throw new ObrigationsFieldNullException();
        }

        if (p.getTitle() == null || p.getTitle().isEmpty()) {
            throw new ObrigationsFieldNullException();
        }

        if (p.getDescription() == null || p.getDescription().isEmpty()) {
            throw new ObrigationsFieldNullException();
        }

        if (p.getFrameworkId() == null) {
            throw new ObrigationsFieldNullException();
        }
        var optional = frameworkRepository.findById(p.getFrameworkId());
        if (optional.isEmpty()) {
            throw new FrameworkNotFoundException();
        }

    }

}
