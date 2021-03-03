package com.study.mobileback.controller;

import com.study.mobileback.dto.EventDto;
import com.study.mobileback.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping(path = "/v1/saveEvent")
    public ResponseEntity<?> saveEvent(@Valid @RequestBody EventDto eventDto) {
        eventService.saveEvent(eventDto);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @DeleteMapping(value = "/v1/event/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @GetMapping(path = "/v1/eventList")
    public ResponseEntity<?> getAnimalList() {
        List<EventDto> eventDtos = eventService.listEvents();
        return new ResponseEntity<>(eventDtos, HttpStatus.OK);
    }
}
