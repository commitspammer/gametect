package com.gametect.api.service;

import com.gametect.api.model.Item;
import com.gametect.api.model.SelectionBox;
import com.gametect.api.model.dto.ItemDTO;
import com.gametect.api.model.dto.SelectionBoxDTO;
import com.gametect.api.repository.ItemRepository;
import com.gametect.api.repository.SelectionBoxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SelectionBoxService {
    @Autowired
    private SelectionBoxRepository selectionBoxRepository;

    @Autowired
    private ItemRepository itemRepository;

    public List<SelectionBoxDTO> findAllSelectionBoxs() {
        return selectionBoxRepository.findAll()
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
            }).toList();
    }

    public Optional<SelectionBoxDTO> findSelectionBoxById(Integer id) {
        return selectionBoxRepository.findById(id)
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
            });
    }

    public SelectionBoxDTO saveSelectionBox(SelectionBoxDTO selectionBoxDTO) {
        SelectionBox s = new SelectionBox();
        s.setProjectId(selectionBoxDTO.getProjectId());
        s.setSubsectionId(selectionBoxDTO.getSubsectionId());
        s.setQuestion(selectionBoxDTO.getQuestion());
        selectionBoxDTO.setId(selectionBoxRepository.save(s).getId());
        for (ItemDTO idto : selectionBoxDTO.getItems()) {
            Item i = new Item();
            i.setSelectionBoxId(selectionBoxDTO.getId());
            i.setDescription(idto.getDescription());
            i.setSelected(idto.getSelected());
            idto.setSelectionBoxId(itemRepository.save(i).getSelectionBoxId());
        }
            
        return selectionBoxDTO;
    }

    public SelectionBox updateSelectionBox(SelectionBox selectionBox) {
        return selectionBoxRepository.update(selectionBox);
    }

    public void deleteAllSelectionBoxs() {
        selectionBoxRepository.deleteAll();
    }

    public void deleteSelectionBoxById(Integer id) {
        List<Integer> items = itemRepository.findAll()
			.stream()
            .filter(i -> i.getSelectionBoxId() == id)
            .map(i -> i.getSelectionBoxId())
            .toList();

        items.forEach(itemRepository::deleteById);
        selectionBoxRepository.deleteById(id);
    }
}