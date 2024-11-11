package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class EventRepository {

    private final List<Event> events;

    public EventRepository() {
        events = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            events.add(new Event("Event_" + i, "description_" + i, Math.round(Math.random() * 1000), new Location("Name" + i, "add" + i, "cap" + i, "desc" + i)));
        }
    }

    public List<Event> findAll() {
        return events;
    }

    public List<Event> searchEvents(String text) {
        return events.stream()
                .filter(event -> event.getName().contains(text) || event.getDescription().contains(text))
                .collect(Collectors.toList());
    }

    public void save(Event event) {
        events.add(event);
    }

    public Optional<Event> findById(String eventId) {
        return events.stream()
                .filter(event -> event.getId().equals(eventId))
                .findFirst();
    }
}
