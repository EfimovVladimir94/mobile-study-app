package com.study.mobileback.controller;

import com.study.mobileback.service.AnimalService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
public class ImageController {
    private final AnimalService animalService;

    @PostMapping(path = "/v1/animalImage")
    public ResponseEntity<?> saveOrUpdate(@RequestParam("file") MultipartFile file) {
        return animalService.saveOrUpdateImage(file);
    }

    @GetMapping(path = "/v1/animalImage")
    public ResponseEntity<?> getImage() {
        return animalService.getImage();
    }

    @DeleteMapping(value = "/v1/animalImage")
    public ResponseEntity<?> deleteAnimal() {
        return animalService.deleteImage();
    }
}
