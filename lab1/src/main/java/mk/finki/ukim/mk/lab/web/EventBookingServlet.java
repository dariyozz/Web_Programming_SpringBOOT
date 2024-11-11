package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.EventBooking;
import mk.finki.ukim.mk.lab.repository.EventRepository;
import mk.finki.ukim.mk.lab.service.EventBookingService;
import mk.finki.ukim.mk.lab.service.EventService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "eventBookingServlet", urlPatterns = "/eventBooking")
public class EventBookingServlet extends HttpServlet {

    private final EventBookingService bookingService;
    private final SpringTemplateEngine templateEngine;
    private final EventService eventService;

    public EventBookingServlet(EventBookingService bookingService, SpringTemplateEngine templateEngine, EventService eventService) {
        this.bookingService = bookingService;
        this.templateEngine = templateEngine;
        this.eventService = eventService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        // Extract parameters from the request
        HttpSession httpSession = req.getSession();
        String eventId = req.getParameter("eventId");
        Event event = eventService.findById(eventId);
        String eventName = event.getName();
        String attendeeName = httpSession.getAttribute("username") == null ? "Unknown" : httpSession.getAttribute("username").toString();
        String attendeeAddress = req.getRemoteAddr();
        int numberOfTickets = Integer.parseInt(req.getParameter("numberOfTickets"));

        // Create the booking object
        EventBooking booking = bookingService.placeBooking(eventName, attendeeName, attendeeAddress, numberOfTickets);


        // Initialize the IWebExchange and WebContext
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);

        // Set attributes in the context
        context.setVariable("booking", booking);


        // Render the template using SpringTemplateEngine
        resp.setContentType("text/html; charset=UTF-8");
        templateEngine.process("bookingConfirmation", context, resp.getWriter());
    }
}
