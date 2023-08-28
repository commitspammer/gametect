package com.gametect.api.service;

import com.gametect.api.model.Image;
import com.gametect.api.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    public List<Image> findAllImages() {
        return imageRepository.findAll();
    }

    public Optional<Image> findImageById(Integer id) {
        return imageRepository.findById(id);
    }

    public Image saveImage(Image image) {
        return imageRepository.save(image);
    }

    public Image updateImage(Image image) {
        return imageRepository.update(image);
    }

    public void deleteAllImages() {
        imageRepository.deleteAll();
    }

    public void deleteImageById(Integer id) {
        imageRepository.deleteById(id);
    }
}