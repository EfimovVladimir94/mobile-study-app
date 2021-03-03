package com.study.mobileback.service;

import com.study.mobileback.dto.EventDto;
import com.study.mobileback.model.entity.Event;
import com.study.mobileback.repository.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.study.mobileback.Util.DataMapper.eventDtoToEvent;
import static com.study.mobileback.Util.DataMapper.listEventToListEventDto;

@Service
@Slf4j
public class EventService {

    @Autowired
    private EventRepository repository;

    public void saveEvent(EventDto dto) {
        Event event = eventDtoToEvent(dto);
        repository.save(event);
    }

    public void deleteEvent(Long id) {
        repository.deleteById(id);
    }

    public List<EventDto> listEvents() {
        List<Event> events = repository.findAll();
        return listEventToListEventDto(events);
    }
}
