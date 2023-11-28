package ca.ubc.cs304.model;

public class Player {
    private final int id;
    private final String first_name;

    private final String last_name;

    private final String country;

    private final String join_date;

    private final int ranking;

    private final int team_id;

    public Player (int id,
                   String first_name,
                   String last_name,
                   String country,
                   String join_date,
                   int ranking,
                   int team_id) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.country = country;
        this.join_date = join_date;
        this.ranking = ranking;
        this.team_id = team_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getCountry() {
        return country;
    }

    public String getJoin_date() {
        return join_date;
    }

    public int getRanking() {
        return ranking;
    }

    public int getTeam_id() {
        return team_id;
    }

    public int getId() {
        return id;
    }
}
