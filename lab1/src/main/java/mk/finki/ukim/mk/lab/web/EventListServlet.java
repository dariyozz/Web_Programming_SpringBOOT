package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.service.EventService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "eventListServlet", urlPatterns = "")
public class EventListServlet extends HttpServlet {

    private final EventService eventService;
    private final SpringTemplateEngine templateEngine;

    public EventListServlet(EventService eventService, SpringTemplateEngine templateEngine) {
        this.eventService = eventService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve search parameters
        String searchText = req.getParameter("searchText");

        // Fetch events based on search criteria
        List<Event> events;
        if (searchText != null && !searchText.isEmpty()) {
            events = eventService.searchEvents(searchText);
        } else {
            events = eventService.listAll();  // Get all events if no search criteria are provided
        }

        if (events.isEmpty()) {
            resp.getWriter().write("<h1>No events found</h1>");
            return;
        }

        // Prepare the WebContext for Thymeleaf rendering
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);

        // Set the list of events in the context
        context.setVariable("events", events);

        // Render the template
        resp.setContentType("text/html; charset=UTF-8");
            templateEngine.process("listEvents", context, resp.getWriter());
    }
}
