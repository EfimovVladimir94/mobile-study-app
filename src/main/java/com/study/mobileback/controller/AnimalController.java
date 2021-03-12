package com.study.mobileback.controller;

import com.study.mobileback.dto.AnimalDto;
import com.study.mobileback.dto.AnimalInfoDto;
import com.study.mobileback.service.AnimalService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

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
        AnimalInfoDto animal = animalService.getAnimal();
        return new ResponseEntity<>(animal, HttpStatus.OK);
    }

    //не используется
    @GetMapping(path = "/v1/animalList")
    public ResponseEntity<?> getAnimalList(@RequestParam Long userId) {
        List<AnimalInfoDto> animalList = animalService.getAnimalList(userId);
        return new ResponseEntity<>(animalList, HttpStatus.OK);
    }
}
