package mk.finki.ukim.mk.lab.dataseeder;

import mk.finki.ukim.mk.lab.model.Location;
import mk.finki.ukim.mk.lab.service.LocationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LocationSeeder implements CommandLineRunner {

    private final LocationService locationService;

    public LocationSeeder(LocationService locationService) {
        this.locationService = locationService;
    }

    @Override
    public void run(String... args)  {
        if (locationService.findAll().isEmpty()) {
        locationService.save(new Location("Central Park", "New York, NY", "5000", "A large public park in New York City"));
        locationService.save(new Location("Golden Gate Park", "San Francisco, CA", "4000", "A large urban park in San Francisco"));
        locationService.save(new Location("Hyde Park", "London, UK", "3500", "A major park in central London"));
        locationService.save(new Location("Ueno Park", "Tokyo, Japan", "3000", "A spacious public park in Tokyo"));
        locationService.save(new Location("Lumphini Park", "Bangkok, Thailand", "2500", "A large park in Bangkok"));
    }
}
}
