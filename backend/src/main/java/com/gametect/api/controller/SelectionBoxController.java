package com.gametect.api.controller;

import com.gametect.api.model.SelectionBox;
import com.gametect.api.model.dto.SelectionBoxDTO;
import com.gametect.api.service.SelectionBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/selection-box")
public class SelectionBoxController {
    @Autowired
    private SelectionBoxService selectionBoxService;

    @GetMapping
    public ResponseEntity<List<SelectionBoxDTO>> getAllSelectionBoxs() {
        List<SelectionBoxDTO> selectionBoxs = selectionBoxService.findAllSelectionBoxs();
        return new ResponseEntity<>(selectionBoxs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SelectionBoxDTO> getSelectionBoxById(@PathVariable Integer id) {
        Optional<SelectionBoxDTO> selectionBox = selectionBoxService.findSelectionBoxById(id);
        return selectionBox.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<SelectionBoxDTO> createSelectionBox(@RequestBody SelectionBoxDTO selectionBox) {
        SelectionBoxDTO savedSelectionBox = selectionBoxService.saveSelectionBox(selectionBox);
        return new ResponseEntity<>(savedSelectionBox, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SelectionBox> updateSelectionBox(@PathVariable Integer id, @RequestBody SelectionBox selectionBox) {
        Optional<SelectionBoxDTO> existingSelectionBox = selectionBoxService.findSelectionBoxById(id);
        if (existingSelectionBox.isPresent()) {
            selectionBox.setId(id);
            SelectionBox updatedSelectionBox = selectionBoxService.updateSelectionBox(selectionBox);
            return new ResponseEntity<>(updatedSelectionBox, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllSelectionBoxs() {
        selectionBoxService.deleteAllSelectionBoxs();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSelectionBoxById(@PathVariable Integer id) {
        Optional<SelectionBoxDTO> selectionBox = selectionBoxService.findSelectionBoxById(id);
        if (selectionBox.isPresent()) {
            selectionBoxService.deleteSelectionBoxById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}