package com.study.mobileback.controller;

import com.study.mobileback.dto.EventDto;
import com.study.mobileback.dto.EventInfoDto;
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
    public ResponseEntity<?> saveEvent(@Valid @RequestBody EventInfoDto eventInfoDto) {
        eventService.saveEvent(eventInfoDto);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @DeleteMapping(value = "/v1/event/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @GetMapping(path = "/v1/eventList")
    public ResponseEntity<?> getEventList() {
        List<EventDto> eventDtos = eventService.listEvents();
        return new ResponseEntity<>(eventDtos, HttpStatus.OK);
    }

    @GetMapping(path = "/v1/event")
    public ResponseEntity<?> getEvent(@RequestParam Long id) {
        EventInfoDto event = eventService.findEvent(id);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }
}
