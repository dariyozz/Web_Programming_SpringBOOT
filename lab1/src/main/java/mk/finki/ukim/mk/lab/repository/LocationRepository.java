package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


public interface LocationRepository extends JpaRepository<Location, String> {


//    private List<Location> locations;
//
//    public LocationRepository() {
//        this.locations = new ArrayList<>();
//        for (int i = 0; i < 4; i++) {
//            locations.add(new Location("Name" + i, "Add" + i, "cap" + i, "desc" + i));
//        }
//    }
//
//    public List<Location> findAll() {
//        return locations;
//    }
}
