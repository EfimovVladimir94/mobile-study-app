package com.study.mobileback.controller;

import com.study.mobileback.dto.AnimalDto;
import com.study.mobileback.dto.AnimalInfoDto;
import com.study.mobileback.service.AnimalService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class AnimalController {

    private final AnimalService animalService;

    @PostMapping(path = "/v1/saveAnimal")
    public ResponseEntity<?> saveAnimal(@Valid @RequestBody AnimalDto animalDto) {
        animalService.saveAnimal(animalDto);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @PostMapping(path = "/v1/editAnimal")
    public ResponseEntity<?> editAnimal(@Valid @RequestBody AnimalDto animalDto) {
        boolean updated = animalService.editAnimal(animalDto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping(value = "/v1/animal/{id}")
    public ResponseEntity<?> deleteAnimal(@PathVariable Long id) {
        boolean deleted = animalService.deleteAnimal(id);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

    @GetMapping(path = "/v1/animal")
    public ResponseEntity<?> getAnimal(@RequestParam Long id) {
        AnimalInfoDto animal = animalService.getAnimal(id);
        return new ResponseEntity<>(animal, HttpStatus.OK);
    }

    @GetMapping(path = "/v1/animalList")
    public ResponseEntity<?> getAnimalList(@RequestParam Long userId) {
        List<AnimalInfoDto> animalList = animalService.getAnimalList(userId);
        return new ResponseEntity<>(animalList, HttpStatus.OK);
    }
}
