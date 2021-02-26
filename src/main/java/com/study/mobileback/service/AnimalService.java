package com.study.mobileback.service;

import com.study.mobileback.dto.AnimalDto;
import com.study.mobileback.entity.Animal;
import com.study.mobileback.repository.AnimalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.study.mobileback.Util.DataMapper.animalDtoToAnimal;

@Service
@Slf4j
public class AnimalService {

    @Autowired
    private AnimalRepository repository;

    public void saveAnimal(AnimalDto animalDto) {
        Animal animal = animalDtoToAnimal(animalDto);
        repository.save(animal);
    }

    public void editAnimal(AnimalDto animalDto) {
        List<Animal> listAnimal = repository.findByUserId(animalDto.getUser().getId());
        Optional<Animal> first = listAnimal.stream()
                .filter(x -> x.getName().equals(animalDto.getName())).findFirst();

        if (first.isPresent()) {
            Animal animal = animalDtoToAnimal(animalDto);
            repository.update(animal.getName(), animal.getCity(), animal.getAge(), animal.getBreed(),
                    animal.getDescription(), animalDto.getId());
        }
    }

    public void deleteAnimal(AnimalDto animalDto) {
        Animal animal = animalDtoToAnimal(animalDto);
        repository.delete(animal);
    }
}
