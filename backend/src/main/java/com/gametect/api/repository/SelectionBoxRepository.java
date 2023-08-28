package com.gametect.api.repository;
import com.gametect.api.model.SelectionBox;

import java.util.List;
import java.util.Optional;

public interface SelectionBoxRepository {

    List<SelectionBox> findAll();
    
    Optional<SelectionBox> findById(Integer id);

    List<SelectionBox> findByProjectId(Integer id);

    SelectionBox save(SelectionBox selectionBox);

    SelectionBox update(SelectionBox selectionBox);

    void deleteAll();
    
    void deleteById(Integer id);
    
}
