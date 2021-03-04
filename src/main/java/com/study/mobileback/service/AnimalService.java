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

    public boolean editAnimal(AnimalDto animalDto) {
        User user = getAuthorizationUser();
        List<Animal> listAnimal = animalRepository.findByUserId(user.getId());
        Optional<Animal> first = listAnimal.stream()
                .filter(x -> x.getName().equals(animalDto.getName())).findFirst();

        if (first.isPresent()) {
            Animal animal = animalDtoToAnimal(animalDto, user);
            animalRepository.update(animal.getName(), animal.getCity(), animal.getAge(), animal.getBreed(),
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
                animalRepository.deleteById(id);
                log.info("delete animal for id: {} success", id);
                return true;
            }
            log.info("an attempt to delete a wrong object");
            return false;
        }
        log.info("delete animal for id: {} failed, existAnimal = null", id);
        return false;
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
