package com.gametect.api.controller;

import com.gametect.api.model.CharacterSheet;
import com.gametect.api.service.CharacterSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/characterSheets")
public class CharacterSheetController {
    @Autowired
    private CharacterSheetService characterSheetService;

    @GetMapping
    public ResponseEntity<List<CharacterSheet>> getAllCharacterSheets() {
        List<CharacterSheet> characterSheets = characterSheetService.findAllCharacterSheets();
        return new ResponseEntity<>(characterSheets, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterSheet> getCharacterSheetById(@PathVariable Integer id) {
        Optional<CharacterSheet> characterSheet = characterSheetService.findCharacterSheetById(id);
        return characterSheet.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<CharacterSheet> createCharacterSheet(@RequestBody CharacterSheet characterSheet) {
        CharacterSheet savedCharacterSheet = characterSheetService.saveCharacterSheet(characterSheet);
        return new ResponseEntity<>(savedCharacterSheet, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CharacterSheet> updateCharacterSheet(@PathVariable Integer id, @RequestBody CharacterSheet characterSheet) {
        Optional<CharacterSheet> existingCharacterSheet = characterSheetService.findCharacterSheetById(id);
        if (existingCharacterSheet.isPresent()) {
            characterSheet.setId(id);
            CharacterSheet updatedCharacterSheet = characterSheetService.updateCharacterSheet(characterSheet);
            return new ResponseEntity<>(updatedCharacterSheet, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllCharacterSheets() {
        characterSheetService.deleteAllCharacterSheets();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacterSheetById(@PathVariable Integer id) {
        Optional<CharacterSheet> characterSheet = characterSheetService.findCharacterSheetById(id);
        if (characterSheet.isPresent()) {
            characterSheetService.deleteCharacterSheetById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}