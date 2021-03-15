package com.study.mobileback.Util;

import com.study.mobileback.dto.*;
import com.study.mobileback.model.entity.*;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class DataMapper {

    public static User userDtoToUser(UserDto userDto) {
        return User.builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
    }

    public static Animal animalDtoToAnimal(AnimalDto animalDto, Image image, User user) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        LocalDate date = LocalDate.parse(animalDto.getRegistrationDate(),formatter);
        Animal animal = Animal.builder()
                .id(user.getId())
                .name(animalDto.getName())
                .city(animalDto.getCity())
                .age(animalDto.getAge())
                .image(image)
                .breed(animalDto.getBreed())
                .description(animalDto.getDescription())
                .gender(animalDto.getGender())
                .ownerName(animalDto.getOwnerName())
                .registrationDate(date)
                .build();
        animal.setUser(user);
        return animal;
    }

    public static AnimalInfoDto animalToAnimalDto(Animal animal, Long id) {
        return AnimalInfoDto.builder()
                .id(id)
                .name(animal.getName())
                .city(animal.getCity())
                .age(animal.getAge())
                .breed(animal.getBreed())
                .description(animal.getDescription())
                .gender(animal.getGender())
                .ownerName(animal.getOwnerName())
                .registrationDate(animal.getRegistrationDate())
                .build();
    }

    public static Event eventDtoToEvent(String payload, byte[] file) {
       EventInfoDto eventInfoDto =  parseEventSaveRequest(payload);
        Event newEvent = Event.builder()
                .name(eventInfoDto.getName())
                .eventType(eventInfoDto.getEventType())
                .description(eventInfoDto.getDescription())
                .phone(eventInfoDto.getPhone())
                .image(file)
                .build();
        newEvent.setLocation(Location.builder()
                .lat(eventInfoDto.getLocation().getLat())
                .lng(eventInfoDto.getLocation().getLng())
                .event(newEvent)
                .build());
        return newEvent;
    }

    public static List<EventMapMarkDto> listEventToListEventDto(List<Event> events) {
        return events.stream()
                .map(x -> EventMapMarkDto.builder()
                        .id(x.getId())
                        .name(x.getName())
                        .eventType(x.getEventType())
                        .description(x.getDescription())
                        .location(LocationDto.builder()
                                .lat(x.getLocation().getLat())
                                .lng(x.getLocation().getLng())
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
                        .lng(event.getLocation().getLng())
                        .build())
                .phone(event.getPhone())
                .build();
    }

    private static EventInfoDto parseEventSaveRequest(String jsonString) {
        JSONObject jsonObject = new JSONObject(jsonString);
        JSONObject location = jsonObject.getJSONObject("location");
        return EventInfoDto.builder()
                .name(jsonObject.getString("name"))
                .eventType(jsonObject.getString("eventType"))
                .description(jsonObject.getString("description"))
                .phone(jsonObject.getString("phone"))
                .location(LocationDto.builder()
                        .lat(location.getDouble("lat"))
                        .lng(location.getDouble("lng"))
                        .build())
                .build();
    }

}
