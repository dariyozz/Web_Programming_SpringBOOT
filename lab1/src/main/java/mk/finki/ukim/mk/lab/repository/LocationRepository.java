package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Location;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LocationRepository {
    private List<Location> locations;

    public LocationRepository() {
        this.locations = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            locations.add(new Location("Name" + i, "Add" + i, "cap" + i, "desc" + i));
        }
    }

    public List<Location> findAll() {
        return locations;
    }
}
