package com.study.mobileback.service;

import com.study.mobileback.Util.DataMapper;
import com.study.mobileback.dto.EventInfoDto;
import com.study.mobileback.dto.EventMapMarkDto;
import com.study.mobileback.model.entity.Event;
import com.study.mobileback.repository.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.study.mobileback.Util.DataMapper.eventDtoToEvent;
import static com.study.mobileback.Util.DataMapper.listEventToListEventDto;

@Service
@Slf4j
public class EventService {

    @Autowired
    private EventRepository repository;

    public ResponseEntity<?> saveEvent(String dto, MultipartFile file) {
        byte[] bytes = new byte[0];
        try {
            bytes = file.getBytes();
            Event event = eventDtoToEvent(dto, bytes);
            repository.save(event);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (IOException e) {
            log.error("save event failure ");
            e.printStackTrace();
            return new ResponseEntity<>("Failure", HttpStatus.OK);
        }
    }

    public void deleteEvent(Long id) {
        repository.deleteById(id);
    }

    public List<EventMapMarkDto> listEvents() {
        List<Event> events = repository.findAll();
        return listEventToListEventDto(events);
    }

    public ResponseEntity<?> findEvent(Long id) {
        Optional<Event> event = repository.findById(id);
        if(event.isPresent()){
            EventInfoDto eventInfoDto = event.map(DataMapper::eventToEventDto).orElse(null);
            return new ResponseEntity<>(eventInfoDto, HttpStatus.OK);
        }
        return new ResponseEntity<>("Failure", HttpStatus.OK);
    }
}
