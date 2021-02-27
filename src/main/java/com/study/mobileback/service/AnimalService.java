package com.study.mobileback.service;

import com.study.mobileback.dto.AnimalDto;
import com.study.mobileback.dto.AnimalInfoDto;
import com.study.mobileback.entity.Animal;
import com.study.mobileback.repository.AnimalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.study.mobileback.Util.DataMapper.*;

@Service
@Slf4j
public class AnimalService {

    @Autowired
    private AnimalRepository repository;

    public void saveAnimal(AnimalDto animalDto) {
        Animal animal = animalDtoToAnimal(animalDto);
        repository.save(animal);
        log.info("animal : {} save success", animal.getName());
    }

    public boolean editAnimal(AnimalDto animalDto) {
        List<Animal> listAnimal = repository.findByUserId(animalDto.getUser().getId());
        Optional<Animal> first = listAnimal.stream()
                .filter(x -> x.getName().equals(animalDto.getName())).findFirst();

        if (first.isPresent()) {
            Animal animal = animalDtoToAnimal(animalDto);
            repository.update(animal.getName(), animal.getCity(), animal.getAge(), animal.getBreed(),
                    animal.getDescription(), animalDto.getId());
            log.info(" animal for id: {} update success", animalDto.getId());
            return true;
        }
        log.info("animal for id: {} update failure", animalDto.getId());
        return false;
    }

    public boolean deleteAnimal(Long id, Long userId) {
        Animal existAnimal = getExistAnimal(id);

        if (existAnimal != null) {
            boolean isOwnerUser = existAnimal.getUser().getId().longValue() == userId.longValue();
            if (isOwnerUser) {
                repository.deleteById(id);
                log.info("delete animal for id: {} success", id);
                return true;
            }
            log.info("an attempt to delete a wrong object");
            return false;
        }
        log.info("delete animal for id: {} failed, existAnimal = null", id);
        return false;
    }

    public Animal getExistAnimal(Long id) {
        Optional<Animal> exist = repository.findById(id);
        return exist.orElse(null);
    }

    public AnimalInfoDto getAnimal(Long id) {
        Animal existAnimal = getExistAnimal(id);
        AnimalInfoDto animalInfoDto = animalToAnimalDto(existAnimal);
        return animalInfoDto;
    }

    public List<AnimalInfoDto> getAnimalList(Long userId) {
        List<Animal> exist = repository.findByUserId(userId);
        return listAnimalToListAnimalInfoDto(exist);
    }
}
