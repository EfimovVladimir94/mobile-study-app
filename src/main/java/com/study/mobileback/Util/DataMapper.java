package com.study.mobileback.Util;

import com.study.mobileback.dto.AnimalDto;
import com.study.mobileback.dto.UserDto;
import com.study.mobileback.entity.Animal;
import com.study.mobileback.entity.User;

public class DataMapper {

    public static User userDtoToUser(UserDto userDto) {
        return User.builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
    }

    public static Animal animalDtoToAnimal(AnimalDto animalDto) {
        return Animal.builder()
                .name(animalDto.getName())
                .city(animalDto.getCity())
                .age(animalDto.getAge())
                .breed(animalDto.getBreed())
                .description(animalDto.getDescription())
                .user(animalDto.getUser())
                .build();
    }

}
