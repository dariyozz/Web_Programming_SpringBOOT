package mk.finki.ukim.mk.lab.web.contollers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.EventBooking;
import mk.finki.ukim.mk.lab.service.EventBookingService;
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
    private final EventBookingService eventBookingService;

    public EventController(EventService eventService, LocationService locationService, EventBookingService eventBookingService) {
        this.eventService = eventService;
        this.locationService = locationService;
        this.eventBookingService = eventBookingService;
    }

    @GetMapping("/all")
    public String getEventsPage(@RequestParam(required = false) String error, HttpServletRequest req, Model model) {
        List<Event> events = eventService.listAll();
        String username = req.getRemoteUser();
        List<EventBooking> eventsBooked = eventBookingService.eventsBooked().stream().filter(e -> e.getAttendeeName().equals(username)).toList();

        model.addAttribute("eventsBooked", eventsBooked);
        model.addAttribute("username", username);
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
        return "redirect:/events/all";
    }

    @GetMapping("/edit/{eventId}")
    public String getEditEventForm(@PathVariable String eventId, Model model) {
        Event event = eventService.findById(eventId);
        model.addAttribute("event", event);
        model.addAttribute("locations", locationService.findAll());
        return "add-event";
    }

    @GetMapping("/details/{eventId}")
    public String getDetails(@PathVariable String eventId, Model model) {
        Event event = eventService.findById(eventId);
        model.addAttribute("event", event);
        List<EventBooking> eventBookings = eventBookingService.eventsBooked()
                .stream().filter(e -> e.getEventName().equals(event.getName())).toList();
        if (eventBookings.isEmpty())
            model.addAttribute("booked", "No one booked this event yet");
        model.addAttribute("eventsBooked", eventBookings);
        return "details";
    }

    @PostMapping("/edit/{eventId}")
    public String editEvent(@PathVariable String eventId, @RequestParam String name,
                            @RequestParam String description, @RequestParam Double popularityScore,
                            @RequestParam String locationId) {
        eventService.update(eventId, name, description, popularityScore, locationId);
        return "redirect:/events/all";
    }

    @PostMapping("/delete/{eventId}")
    public String deleteEvent(@PathVariable String eventId) {
        eventService.delete(eventId);
        return "redirect:/events/all";
    }
}
