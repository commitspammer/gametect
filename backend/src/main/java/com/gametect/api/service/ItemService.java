package com.gametect.api.service;

import com.gametect.api.model.Item;
import com.gametect.api.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public List<Item> findAllItems() {
        return itemRepository.findAll();
    }

    public List<Item> findItemBySelectionBoxId(Integer id) {
        return itemRepository.findBySelectionBoxId(id);
    }

    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    public void deleteAllItems() {
        itemRepository.deleteAll();
    }

    public void deleteItemById(Integer id) {
        itemRepository.deleteById(id);
    }
}