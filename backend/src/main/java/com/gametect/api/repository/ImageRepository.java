package com.gametect.api.repository;
import com.gametect.api.model.Image;

import java.util.List;
import java.util.Optional;

public interface ImageRepository {

    List<Image> findAll();
    
    Optional<Image> findById(Integer id);

    List<Image> findByProjectId(Integer id);

    Image save(Image image);

    Image update(Image image);

    void deleteAll();
    
    void deleteById(Integer id);
    
}
