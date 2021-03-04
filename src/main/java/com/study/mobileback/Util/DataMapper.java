package com.study.mobileback.Util;

import com.study.mobileback.dto.AnimalDto;
import com.study.mobileback.dto.AnimalInfoDto;
import com.study.mobileback.dto.EventDto;
import com.study.mobileback.dto.UserDto;
import com.study.mobileback.model.entity.Animal;
import com.study.mobileback.model.entity.Event;
import com.study.mobileback.model.entity.User;

import java.util.List;
import java.util.stream.Collectors;

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

    public static AnimalInfoDto animalToAnimalDto(Animal animal) {
        return AnimalInfoDto.builder()
                .id(animal.getId())
                .name(animal.getName())
                .city(animal.getCity())
                .age(animal.getAge())
                .breed(animal.getBreed())
                .description(animal.getDescription())
                .build();
    }

    public static List<AnimalInfoDto> listAnimalToListAnimalInfoDto(List<Animal> animal) {
        return animal.stream()
                .map(x -> AnimalInfoDto.builder()
                        .id(x.getId())
                        .name(x.getName())
                        .city(x.getCity())
                        .age(x.getAge())
                        .breed(x.getBreed())
                        .description(x.getDescription())
                        .build())
                .collect(Collectors.toList());
    }

    public static Event eventDtoToEvent(EventDto eventDto) {
        return Event.builder()
                .name(eventDto.getName())
                .eventType(eventDto.getEventType())
                .description(eventDto.getDescription())
                .location(eventDto.getLocation())
                .phone(eventDto.getPhone())
                .image(eventDto.getImage())
                .build();
    }

    public static List<EventDto> listEventToListEventDto(List<Event> events) {
        return events.stream()
                .map(x -> EventDto.builder()
                        .id(x.getId())
                        .name(x.getName())
                        .eventType(x.getEventType())
                        .description(x.getDescription())
                        .image(x.getImage())
                        .location(x.getLocation())
                        .phone(x.getPhone())
                        .build())
                .collect(Collectors.toList());
    }
}
