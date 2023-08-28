package com.gametect.api.repository;
import com.gametect.api.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {

    List<Item> findAll();
    
    List<Item> findBySelectionBoxId(Integer id);

    Item save(Item item);

    void deleteAll();
    
    void deleteById(Integer id);
    
}
