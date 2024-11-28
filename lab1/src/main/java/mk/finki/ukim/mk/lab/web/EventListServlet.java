package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.EventBooking;
import mk.finki.ukim.mk.lab.service.EventBookingService;
import mk.finki.ukim.mk.lab.service.EventService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

//@WebServlet(name = "eventListServlet", urlPatterns = "/listEvents")
public class EventListServlet extends HttpServlet {

    private final EventService eventService;
    private final EventBookingService eventBookingService;
    private final SpringTemplateEngine templateEngine;

    public EventListServlet(EventService eventService, EventBookingService eventBookingService, SpringTemplateEngine templateEngine) {
        this.eventService = eventService;
        this.eventBookingService = eventBookingService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve search parameters
        String searchText = req.getParameter("searchText");
        String username = req.getSession().getAttribute("username") == null ?
                "Unknown" : req.getSession().getAttribute("username").toString();
        // Fetch events based on search criteria
        List<Event> events;
        if (searchText != null && !searchText.isEmpty()) {
            events = eventService.searchEvents(searchText);
        } else {
            events = eventService.listAll();  // Get all events if no search criteria are provided
        }

        List<EventBooking> eventsBooked = eventBookingService.eventsBooked();
        // Prepare the WebContext for Thymeleaf rendering
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);

        // Set the list of events in the context
        context.setVariable("events", events);
        context.setVariable("eventsBooked", eventsBooked);
        context.setVariable("username", username);
        // Render the template
        resp.setContentType("text/html; charset=UTF-8");
        templateEngine.process("listEvents", context, resp.getWriter());
    }
}
