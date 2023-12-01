package ca.ubc.cs304.model;

public class Venue {
    private final String name;
    private final String city;
    private final String country;
    private final int capacity;

    public Venue(String name, String city, String country, int capacity) {
        this.name = name;
        this.city = city;
        this.country = country;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public int getCapacity() {
        return capacity;
    }
}