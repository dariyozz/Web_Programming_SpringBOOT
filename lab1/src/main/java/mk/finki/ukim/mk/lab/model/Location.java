package mk.finki.ukim.mk.lab.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Location {
    private String Id;
    private String name;
    private String address;
    private String capacity;
    private String desc;

    public Location(String name, String address, String capacity, String desc) {
        this.Id = UUID.randomUUID().toString();
        this.name = name;
        this.address = address;
        this.capacity = capacity;
        this.desc = desc;
    }
}
