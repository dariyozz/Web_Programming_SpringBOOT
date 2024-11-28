package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Data
@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String address;
    private String capacity;
    private String description;

    @OneToMany(mappedBy = "location")
    private List<Event> events;

    public Location(String name, String address, String capacity, String desc) {
        this.name = name;
        this.address = address;
        this.capacity = capacity;
        this.description = desc;
    }

    public Location() {
    }
}
