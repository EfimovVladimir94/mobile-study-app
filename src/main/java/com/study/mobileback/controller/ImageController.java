package com.study.mobileback.controller;

import com.study.mobileback.service.AnimalService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
public class ImageController {
    private final AnimalService animalService;

    @PostMapping(path = "/v1/animalImage")
    public ResponseEntity<?> saveOrUpdate(@RequestParam("file") MultipartFile file) {
        return animalService.saveOrUpdateImage(file);
    }

    @DeleteMapping(value = "/v1/animalImage")
    public ResponseEntity<?> deleteAnimal() {
        return animalService.deleteImage();
    }
}
