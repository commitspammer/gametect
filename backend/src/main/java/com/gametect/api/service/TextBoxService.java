package com.gametect.api.service;

import com.gametect.api.model.TextBox;
import com.gametect.api.repository.TextBoxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TextBoxService {
    @Autowired
    private TextBoxRepository textBoxRepository;

    public List<TextBox> findAllTextBoxs() {
        return textBoxRepository.findAll();
    }

    public Optional<TextBox> findTextBoxById(Integer id) {
        return textBoxRepository.findById(id);
    }

    public TextBox saveTextBox(TextBox textBox) {
        return textBoxRepository.save(textBox);
    }

    public TextBox updateTextBox(TextBox textBox) {
        return textBoxRepository.update(textBox);
    }

    public void deleteAllTextBoxs() {
        textBoxRepository.deleteAll();
    }

    public void deleteTextBoxById(Integer id) {
        textBoxRepository.deleteById(id);
    }
}