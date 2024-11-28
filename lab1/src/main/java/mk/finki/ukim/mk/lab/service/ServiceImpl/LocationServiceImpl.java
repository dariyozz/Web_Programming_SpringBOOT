package mk.finki.ukim.mk.lab.service.ServiceImpl;

import lombok.RequiredArgsConstructor;
import mk.finki.ukim.mk.lab.model.Location;
import mk.finki.ukim.mk.lab.repository.LocationRepository;
import mk.finki.ukim.mk.lab.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;

    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    public void save(Location location) {
        locationRepository.save(location);
    }
}
