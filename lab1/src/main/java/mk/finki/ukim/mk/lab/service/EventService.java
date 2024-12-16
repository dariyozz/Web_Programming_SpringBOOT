package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Event;

import java.util.List;


public interface EventService {

    List<Event> listAll();

    List<Event> searchEvents(String text);

    Event findById(String eventId);

    void update(String eventId, String name, String description, Double popularityScore, String locationId);

    void delete(String id);

    void save(String name, String description, Integer popularityScore, String locationId);
}