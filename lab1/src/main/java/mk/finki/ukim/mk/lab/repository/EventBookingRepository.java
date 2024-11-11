package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.EventBooking;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EventBookingRepository {
    private List<EventBooking> eventBookings;

    public EventBookingRepository(List<EventBooking> eventBookings) {
        this.eventBookings = new ArrayList<>();
    }

    public List<EventBooking> findAll() {
        return eventBookings;
    }

    public void add(EventBooking e) {
        eventBookings.add(e);
    }
}
