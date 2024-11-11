package mk.finki.ukim.mk.lab.service.ServiceImpl;

import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.repository.EventRepository;
import mk.finki.ukim.mk.lab.repository.LocationRepository;
import mk.finki.ukim.mk.lab.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, LocationRepository locationRepository) {
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Event> listAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> searchEvents(String text) {
        return eventRepository.searchEvents(text);
    }

    @Override
    public Event findById(String eventId) {
        return eventRepository.findById(eventId).orElse(null);
    }

    @Override
    public void update(String eventId, String name, String description, Integer popularityScore, String locationId) {
        Event event = findById(eventId);
        if (event == null) return;
        event.setDescription(description);
        event.setName(name);
        event.setPopularityScore(popularityScore);
        event.setLocation(locationRepository.findAll().stream().filter(location -> location.getId().equals(locationId)).findFirst().orElse(null));
    }

    @Override
    public void delete(String id) {
        eventRepository.findAll().removeIf(event -> event.getId().equals(id));
    }

    @Override
    public void save(String name, String description, Integer popularityScore, String locationId) {
        Event event = new Event();
        event.setName(name);
        event.setDescription(description);
        event.setPopularityScore(popularityScore);
        event.setLocation(locationRepository.findAll().stream().filter(location -> location.getId().equals(locationId)).findFirst().orElse(null));
        eventRepository.save(event);
    }
}