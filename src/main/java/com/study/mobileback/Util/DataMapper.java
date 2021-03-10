package com.study.mobileback.Util;

import com.study.mobileback.dto.*;
import com.study.mobileback.model.entity.Animal;
import com.study.mobileback.model.entity.Event;
import com.study.mobileback.model.entity.Location;
import com.study.mobileback.model.entity.User;
import org.json.JSONObject;

import java.util.List;
import java.util.stream.Collectors;

public class DataMapper {

    public static User userDtoToUser(UserDto userDto) {
        return User.builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
    }

    public static Animal animalDtoToAnimal(String payload, byte[] file, User user) {
        AnimalDto animalDto = parseAnimalSaveRequest(payload);
        Animal animal = Animal.builder()
                .id(user.getId())
                .name(animalDto.getName())
                .city(animalDto.getCity())
                .age(animalDto.getAge())
                .image(file)
                .breed(animalDto.getBreed())
                .description(animalDto.getDescription())
                .gender(animalDto.getGender())
                .ownerName(animalDto.getOwnerName())
                .build();
        animal.setUser(user);
        return animal;
    }

    public static AnimalInfoDto animalToAnimalDto(Animal animal) {
        return AnimalInfoDto.builder()
                .id(animal.getId())
                .name(animal.getName())
                .city(animal.getCity())
                .age(animal.getAge())
                .breed(animal.getBreed())
                .description(animal.getDescription())
                .ownerName(animal.getOwnerName())
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

    private static AnimalDto parseAnimalSaveRequest(String jsonString) {
        JSONObject jsonObject = new JSONObject(jsonString);
        return AnimalDto.builder()
                .name(jsonObject.getString("name"))
                .city(jsonObject.getString("city"))
                .age(jsonObject.getInt("age"))
                .breed(jsonObject.getString("breed"))
                .description(jsonObject.getString("description"))
                .gender(jsonObject.getString("gender"))
                .ownerName("ownerName")
                .build();
    }
}
