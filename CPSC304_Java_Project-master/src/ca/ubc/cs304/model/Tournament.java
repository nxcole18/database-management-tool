package ca.ubc.cs304.model;

public class Tournament {
    private final String name;
    private final String start_date;
    private final String end_date;
    private final String format;
    private final String venue_name;
    private final String venue_city;
    private final int sponsor_id;
    private final int winning_team;

    public Tournament(String name,
                      String start_date,
                      String end_date,
                      String format,
                      String venue_name,
                      String venue_city,
                      int sponsor_id,
                      int winning_team) {
        this.name = name;
        this.start_date = start_date;
        this.end_date = end_date;
        this.format = format;
        this.venue_name = venue_name;
        this.venue_city = venue_city;
        this.sponsor_id = sponsor_id;
        this.winning_team = winning_team;
    }

    public String getName() {
        return name;
    }

    public String getStart_date() {
        return start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public String getFormat() {
        return format;
    }

    public String getVenue_name() {
        return venue_name;
    }

    public String getVenue_city() {
        return venue_city;
    }

    public int getSponsor_id() {
        return sponsor_id;
    }

    public int getWinning_team() {
        return winning_team;
    }
}