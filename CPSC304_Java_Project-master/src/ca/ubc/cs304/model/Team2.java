package ca.ubc.cs304.model;

public class Team2 {
    private final int id;
    private final String name;
    private final String organization;

    public Team2(int id, String name, String organization) {
        this.id = id;
        this.name = name;
        this.organization = organization;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOrganization() {
        return organization;
    }
}
