package com.gametect.api.service;

import com.gametect.api.model.CharacterSheet;
import com.gametect.api.repository.CharacterSheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CharacterSheetService {
    @Autowired
    private CharacterSheetRepository characterSheetRepository;

    public List<CharacterSheet> findAllCharacterSheets() {
        return characterSheetRepository.findAll();
    }

    public Optional<CharacterSheet> findCharacterSheetById(Integer id) {
        return characterSheetRepository.findById(id);
    }

    public CharacterSheet saveCharacterSheet(CharacterSheet characterSheet) {
        return characterSheetRepository.save(characterSheet);
    }

    public CharacterSheet updateCharacterSheet(CharacterSheet characterSheet) {
        return characterSheetRepository.update(characterSheet);
    }

    public void deleteAllCharacterSheets() {
        characterSheetRepository.deleteAll();
    }

    public void deleteCharacterSheetById(Integer id) {
        characterSheetRepository.deleteById(id);
    }
}