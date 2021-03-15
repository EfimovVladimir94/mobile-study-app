package com.study.mobileback.controller;

import com.study.mobileback.dto.AnimalDto;
import com.study.mobileback.service.AnimalService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@Slf4j
public class AnimalController {

    private final AnimalService animalService;

    @PostMapping(path = "/v1/animal")
    public ResponseEntity<?> saveOrUpdate(@Valid @RequestBody AnimalDto animalDto) {
        return animalService.saveOrUpdateInfo(animalDto);
    }

    @DeleteMapping(value = "/v1/animal")
    public ResponseEntity<?> deleteAnimal() {
        return animalService.deleteAnimal();
    }

    @GetMapping(path = "/v1/animal")
    public ResponseEntity<?> getAnimal() {
        return animalService.getAnimal();
    }
}
