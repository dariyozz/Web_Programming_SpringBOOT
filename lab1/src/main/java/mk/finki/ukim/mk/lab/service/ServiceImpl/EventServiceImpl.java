package mk.finki.ukim.mk.lab.service.ServiceImpl;

import lombok.RequiredArgsConstructor;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import mk.finki.ukim.mk.lab.repository.EventRepository;
import mk.finki.ukim.mk.lab.repository.LocationRepository;
import mk.finki.ukim.mk.lab.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;


    public List<Event> listAll() {
        return eventRepository.findAll();
    }

    public List<Event> searchEvents(String text) {
        return eventRepository.searchByNameAndDescription(text, text);
    }

    public Event findById(String eventId) {
        return eventRepository.findById(eventId).orElse(null);
    }

    public void update(String eventId, String name, String description, Double popularityScore, String locationId) {
        Event event = findById(eventId);
        if (event == null) return;
        event.setDescription(description);
        event.setName(name);
        event.setPopularityScore(popularityScore);
        Location location = locationRepository.findById(locationId).orElse(null);
        event.setLocation(location);
        eventRepository.save(event);

    }

    public void delete(String id) {
        Event event = eventRepository.findById(id).orElse(null);
        assert event != null;
        eventRepository.delete(event);
    }


    public void save(String name, String description, Integer popularityScore, String locationId) {
        Event event = new Event();
        event.setName(name);
        event.setDescription(description);
        event.setPopularityScore(popularityScore);
        Location location = locationRepository.findById(locationId).orElse(null);
        event.setLocation(location);
        eventRepository.save(event);
    }
}