package com.study.mobileback.service;

import com.study.mobileback.dto.AnimalDto;
import com.study.mobileback.dto.AnimalInfoDto;
import com.study.mobileback.model.entity.Animal;
import com.study.mobileback.model.entity.Image;
import com.study.mobileback.model.entity.User;
import com.study.mobileback.repository.AnimalRepository;
import com.study.mobileback.repository.ImageRepository;
import com.study.mobileback.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    @Autowired
    private ImageRepository imageRepository;

    public ResponseEntity<?> saveOrUpdateInfo(AnimalDto animalDto) {

        Optional<Image> image = imageRepository.findById(getAuthorizationUser().getId());
        Image temp = image.orElse(null);
        Animal animal = animalDtoToAnimal(animalDto, temp, getAuthorizationUser());
        try {
            animalRepository.save(animal);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return new ResponseEntity<>("Failure", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> deleteAnimal() {
        Long id = getAuthorizationUser().getId();
        Animal existAnimal = getExistAnimal(id);
        if (existAnimal != null) {
            animalRepository.deleteById(id);
            log.info("delete animal for id: {} success", id);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
        log.info("delete animal for id: {} failed, existAnimal = null", id);
        return new ResponseEntity<>("Failure", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> saveOrUpdateImage(MultipartFile file) {

        byte[] bytes = new byte[0];
        try {
            bytes = file.getBytes();
            Image image = Image.builder()
                    .id(getAuthorizationUser().getId())
                    .bytes(bytes)
                    .animal(getExistAnimal(getAuthorizationUser().getId()))
                    .build();
            try {
                imageRepository.save(image);
            } catch (Exception e) {
                log.error(e.getLocalizedMessage());
            }

            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (IOException e) {
            log.error("save event failure ");
            e.printStackTrace();
            return new ResponseEntity<>("Failure", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> deleteImage() {
        Optional<Image> existImage = imageRepository.findById(getAuthorizationUser().getId());
        if (existImage.isPresent()) {
            imageRepository.deleteById(getAuthorizationUser().getId());
            log.info("delete animal for id: {} success", existImage.get().getId());
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
        log.info("delete image failed, existImage = null");
        return new ResponseEntity<>("Failure", HttpStatus.BAD_REQUEST);
    }

    private User getAuthorizationUser() {
        org.springframework.security.core.userdetails.User principal =
                (org.springframework.security.core.userdetails.User)
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> existUser = userRepository.findByEmail(principal.getUsername());
        return existUser.orElse(null);
    }

    public AnimalInfoDto getAnimal() {
        User authorizationUser = getAuthorizationUser();
        Animal existAnimal = getExistAnimal(authorizationUser.getId());
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
