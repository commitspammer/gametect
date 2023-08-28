package com.gametect.api.controller;

import com.gametect.api.model.TextBox;
import com.gametect.api.service.TextBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/textBox")
public class TextBoxController {
    @Autowired
    private TextBoxService textBoxService;

    @GetMapping
    public ResponseEntity<List<TextBox>> getAllTextBoxs() {
        List<TextBox> textBoxs = textBoxService.findAllTextBoxs();
        return new ResponseEntity<>(textBoxs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TextBox> getTextBoxById(@PathVariable Integer id) {
        Optional<TextBox> textBox = textBoxService.findTextBoxById(id);
        return textBox.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<TextBox> createTextBox(@RequestBody TextBox textBox) {
        TextBox savedTextBox = textBoxService.saveTextBox(textBox);
        return new ResponseEntity<>(savedTextBox, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TextBox> updateTextBox(@PathVariable Integer id, @RequestBody TextBox textBox) {
        Optional<TextBox> existingTextBox = textBoxService.findTextBoxById(id);
        if (existingTextBox.isPresent()) {
            textBox.setId(id);
            TextBox updatedTextBox = textBoxService.updateTextBox(textBox);
            return new ResponseEntity<>(updatedTextBox, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllTextBoxs() {
        textBoxService.deleteAllTextBoxs();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTextBoxById(@PathVariable Integer id) {
        Optional<TextBox> textBox = textBoxService.findTextBoxById(id);
        if (textBox.isPresent()) {
            textBoxService.deleteTextBoxById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}