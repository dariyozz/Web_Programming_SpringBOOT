package mk.finki.ukim.mk.lab.web.contollers;

import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.service.EventService;
import mk.finki.ukim.mk.lab.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;
    private final LocationService locationService;

    public EventController(EventService eventService, LocationService locationService) {
        this.eventService = eventService;
        this.locationService = locationService;
    }

    @GetMapping
    public String getEventsPage(@RequestParam(required = false) String error, Model model) {
        List<Event> events = eventService.listAll();
        model.addAttribute("events", events);
        model.addAttribute("error", error);
        return "listEvents";
    }

    @GetMapping("/add-event")
    public String getAddEventPage(Model model) {
        model.addAttribute("locations", locationService.findAll());
        return "add-event";
    }

    @PostMapping("/add")
    public String saveEvent(@RequestParam String name, @RequestParam String description,
                            @RequestParam Integer popularityScore, @RequestParam String locationId) {
        eventService.save(name, description, popularityScore, locationId);
        return "redirect:/listEvents";
    }

    @GetMapping("/edit/{eventId}")
    public String getEditEventForm(@PathVariable String eventId, Model model) {
        Event event = eventService.findById(eventId);
        model.addAttribute("event", event);
        model.addAttribute("locations", locationService.findAll());
        return "add-event";
    }

    @PostMapping("/edit/{eventId}")
    public String editEvent(@PathVariable String eventId, @RequestParam String name,
                            @RequestParam String description, @RequestParam Integer popularityScore,
                            @RequestParam String locationId) {
        eventService.update(eventId, name, description, popularityScore, locationId);
        return "redirect:/listEvents";
    }

    @PostMapping("/delete/{eventId}")
    public String deleteEvent(@PathVariable String eventId) {
        eventService.delete(eventId);
        return "redirect:/listEvents";
    }
}
