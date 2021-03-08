package com.study.mobileback.service;

import com.study.mobileback.Util.DataMapper;
import com.study.mobileback.dto.EventDto;
import com.study.mobileback.dto.EventInfoDto;
import com.study.mobileback.dto.EventMapMarkDto;
import com.study.mobileback.model.entity.Event;
import com.study.mobileback.repository.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.study.mobileback.Util.DataMapper.*;

@Service
@Slf4j
public class EventService {

    @Autowired
    private EventRepository repository;

    public void saveEvent(String dto, MultipartFile file) {
        byte[] bytes = new byte[0];
        try {
            bytes = file.getBytes();
            Event event = eventDtoToEvent(dto, bytes);
            repository.save(event);
        } catch (IOException e) {
            log.error("save event failure ");
            e.printStackTrace();
        }
    }

    public void deleteEvent(Long id) {
        repository.deleteById(id);
    }

    public List<EventMapMarkDto> listEvents() {
        List<Event> events = repository.findAll();
        return listEventToListEventDto(events);
    }

    public EventInfoDto findEvent(Long id) {
        Optional<Event> event = repository.findById(id);
        return event.map(DataMapper::eventToEventDto).orElse(null);
    }
}
