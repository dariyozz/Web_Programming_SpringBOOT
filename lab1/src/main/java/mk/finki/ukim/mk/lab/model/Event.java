package mk.finki.ukim.mk.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.Random;
import java.util.UUID;

@Data
public class Event {
    private String Id;
    private String name;
    private String description;
    private double popularityScore;
    private Location location;

    public Event(String name, String description, double popularityScore, Location location) {
        Id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.popularityScore = popularityScore;
        this.location = location;
    }

    public Event() {
        Id = UUID.randomUUID().toString();
    }
}
