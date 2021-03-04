package com.study.mobileback.service;

import com.study.mobileback.dto.AnimalDto;
import com.study.mobileback.dto.AnimalInfoDto;
import com.study.mobileback.model.entity.Animal;
import com.study.mobileback.model.entity.User;
import com.study.mobileback.repository.AnimalRepository;
import com.study.mobileback.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.study.mobileback.Util.DataMapper.*;

@Service
@Slf4j
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> saveAnimal(AnimalDto animalDto) {
        User user = getAuthorizationUser();
        if (user != null) {
            Animal existAnimal = getExistAnimal(user.getId());
            if (existAnimal == null) {
                Animal animal = animalDtoToAnimal(animalDto, user);
                animalRepository.save(animal);
                log.info("animal : {} save success", animal.getName());
                return new ResponseEntity<>("Success", HttpStatus.OK);
            }
        }
        log.info("animal : {} save failure", animalDto.getName());
        return new ResponseEntity<>("Failure", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> editAnimal(AnimalDto animalDto) {
        User user = getAuthorizationUser();
        List<Animal> listAnimal = animalRepository.findByUserId(user.getId());
        Optional<Animal> first = listAnimal.stream()
                .filter(x -> x.getName().equals(animalDto.getName())).findFirst();

        if (first.isPresent()) {
            Animal animal = animalDtoToAnimal(animalDto, user);
            animalRepository.update(animal.getName(), animal.getCity(), animal.getAge(), animal.getBreed(),
                    animal.getDescription(), user.getId());
            log.info(" animal for id: {} update success", user.getId());
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
        log.info("animal for id: {} update failure", animalDto.getName());
        return new ResponseEntity<>("Failure", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> deleteAnimal(Long id) {
        Animal existAnimal = getExistAnimal(id);
        if (existAnimal != null) {
                animalRepository.deleteById(id);
                log.info("delete animal for id: {} success", id);
                return new ResponseEntity<>("Success", HttpStatus.OK);
        }
        log.info("delete animal for id: {} failed, existAnimal = null", id);
        return new ResponseEntity<>("Failure", HttpStatus.BAD_REQUEST);
    }

    private User getAuthorizationUser() {
        org.springframework.security.core.userdetails.User principal =
                (org.springframework.security.core.userdetails.User)
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> existUser = userRepository.findByEmail(principal.getUsername());
        return existUser.orElse(null);
    }

    public AnimalInfoDto getAnimal(Long id) {
        Animal existAnimal = getExistAnimal(id);
        return animalToAnimalDto(existAnimal);
    }

    public Animal getExistAnimal(Long id) {
        Optional<Animal> exist = animalRepository.findById(id);
        return exist.orElse(null);
    }

    public List<AnimalInfoDto> getAnimalList(Long userId) {
        List<Animal> exist = animalRepository.findByUserId(userId);
        return listAnimalToListAnimalInfoDto(exist);
    }
}
