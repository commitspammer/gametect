package com.gametect.api.repository;

import java.util.List;
import java.util.Optional;

import com.gametect.api.model.CharacterSheet;

public interface CharacterSheetRepository {
    List<CharacterSheet> findAll();
    
    Optional<CharacterSheet> findById(Integer id);

    List<CharacterSheet> findByProjectId(Integer id);

    CharacterSheet save(CharacterSheet characterSheet);

    CharacterSheet update(CharacterSheet characterSheet);

    void deleteAll();
    
    void deleteById(Integer id);
}
