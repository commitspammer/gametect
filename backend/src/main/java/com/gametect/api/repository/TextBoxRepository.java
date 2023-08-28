package com.gametect.api.repository;
import com.gametect.api.model.TextBox;

import java.util.List;
import java.util.Optional;

public interface TextBoxRepository {

    List<TextBox> findAll();
    
    Optional<TextBox> findById(Integer id);

    List<TextBox> findByProjectId(Integer id);


    TextBox save(TextBox textBox);

    TextBox update(TextBox textBox);

    void deleteAll();
    
    void deleteById(Integer id);
    
}
