package mk.finki.ukim.mk.lab.web.contollers;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.EventBooking;
import mk.finki.ukim.mk.lab.service.EventBookingService;
import mk.finki.ukim.mk.lab.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/eventBooking")
public class EventBookingController {

    private final EventBookingService bookingService;
    private final EventService eventService;

    public EventBookingController(EventBookingService bookingService, EventService eventService) {
        this.bookingService = bookingService;
        this.eventService = eventService;
    }

    @PostMapping
    public String placeBooking(@RequestParam String eventId,
                               @RequestParam int numberOfTickets,
                               HttpSession session,
                               HttpServletRequest req,
                               Model model) {
        Event event = eventService.findById(eventId);
        String eventName = event.getName();
        String attendeeName = req.getRemoteUser();
        String attendeeAddress = req.getRemoteAddr();

        EventBooking booking = bookingService.placeBooking(eventName, attendeeName, attendeeAddress, numberOfTickets);

        model.addAttribute("booking", booking);
        return "bookingConfirmation";
    }
}