package com.study.mobileback.Util;

import com.study.mobileback.dto.*;
import com.study.mobileback.model.entity.Animal;
import com.study.mobileback.model.entity.Event;
import com.study.mobileback.model.entity.Location;
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

    public static Animal animalDtoToAnimal(AnimalDto animalDto, User user) {
        return Animal.builder()
                .name(animalDto.getName())
                .city(animalDto.getCity())
                .age(animalDto.getAge())
                .breed(animalDto.getBreed())
                .description(animalDto.getDescription())
                .user(user)
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

    public static Event eventDtoToEvent(EventInfoDto eventInfoDto) {
        Event newEvent = Event.builder()
                .name(eventInfoDto.getName())
                .eventType(eventInfoDto.getEventType())
                .description(eventInfoDto.getDescription())
                .phone(eventInfoDto.getPhone())
                .image(eventInfoDto.getImage())
                .build();
        newEvent.setLocation(Location.builder()
                .lat(eventInfoDto.getLocation().getLat())
                .lng(eventInfoDto.getLocation().getLng())
                .event(newEvent)
                .build());
        return newEvent;
    }

    public static List<EventDto> listEventToListEventDto(List<Event> events) {
        return events.stream()
                .map(x -> EventDto.builder()
                        .id(x.getId())
                        .name(x.getName())
                        .eventType(x.getEventType())
                        .description(x.getDescription())
                        .image(x.getImage())
                        .location(LocationDto.builder()
                                .lat(x.getLocation().getLat())
                                .lng(x.getLocation().getLat())
                                .build())
                        .build())
                .collect(Collectors.toList());
    }

    public static EventInfoDto eventToEventDto(Event event) {
        return EventInfoDto.builder()
                .id(event.getId())
                .name(event.getName())
                .eventType(event.getEventType())
                .description(event.getDescription())
                .image(event.getImage())
                .location(LocationDto.builder()
                        .lat(event.getLocation().getLat())
                        .lng(event.getLocation().getLat())
                        .build())
                .phone(event.getPhone())
                .build();
    }
}
