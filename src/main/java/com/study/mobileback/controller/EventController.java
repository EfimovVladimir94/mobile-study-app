package com.study.mobileback.controller;

import com.study.mobileback.dto.EventMapMarkDto;
import com.study.mobileback.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping(path = "/v1/saveEvent")
    public ResponseEntity<?> saveEvent(@RequestParam("event") String eventInfoDto,
                                       @RequestParam("file") MultipartFile file) {
        return eventService.saveEvent(eventInfoDto, file);
    }

    @DeleteMapping(value = "/v1/event/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @GetMapping(path = "/v1/eventList")
    public ResponseEntity<?> getEventList() {
        List<EventMapMarkDto> eventDtos = eventService.listEvents();
        return new ResponseEntity<>(eventDtos, HttpStatus.OK);
    }

    @GetMapping(path = "/v1/event")
    public ResponseEntity<?> getEvent(@RequestParam Long id) {
        return eventService.findEvent(id);
    }
}
