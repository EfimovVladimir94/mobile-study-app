package com.study.mobileback.controller;

import com.study.mobileback.dto.AnimalDto;
import com.study.mobileback.service.AnimalService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@AllArgsConstructor
public class AnimalController {

    private final AnimalService animalService;

    @PostMapping(path = "/v1/animal")
    public ResponseEntity<?> addAnimal(@RequestBody AnimalDto animalDto) {
        animalService.saveAnimal(animalDto);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @PostMapping(path = "/v1/editAnimal")
    public ResponseEntity<?> editAnimal(@Valid @RequestBody AnimalDto animalDto) {
        animalService.editAnimal(animalDto);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

//    @PostMapping(path = "/v1/editAnimal")
//    public ResponseEntity<?> deleteAnimal(@RequestBody Long id) {
//        animalService.deleteAnimal(animalDto);
//        return new ResponseEntity<>("Success", HttpStatus.OK);
//    }
}
