package mk.finki.ukim.mk.lab.service.ServiceImpl;

import mk.finki.ukim.mk.lab.model.EventBooking;
import mk.finki.ukim.mk.lab.repository.EventBookingRepository;
import mk.finki.ukim.mk.lab.service.EventBookingService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EventBookingServiceImpl implements EventBookingService {

    private final EventBookingRepository eventBookingRepository;

    public EventBookingServiceImpl(EventBookingRepository eventBookingRepository) {
        this.eventBookingRepository = eventBookingRepository;
    }

    @Override
    public EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets) {
        EventBooking eventBooking = new EventBooking(eventName, attendeeName, attendeeAddress, (long) numberOfTickets);
        EventBooking check = eventBookingRepository.findAll().stream().filter(e -> e.equals(eventBooking)).findFirst().orElse(null);
        if (check != null) {
            check.setNumberOfTickets(check.getNumberOfTickets() + eventBooking.getNumberOfTickets());
            return check;
        }
        eventBookingRepository.add(eventBooking);
        return eventBooking;
    }

    @Override
    public List<EventBooking> eventsBooked() {
        return eventBookingRepository.findAll();
    }
}
