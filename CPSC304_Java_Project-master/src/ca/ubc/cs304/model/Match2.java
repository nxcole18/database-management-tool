package ca.ubc.cs304.model;

public class Match2 {
    private final int match_number;
    private final String tournament_name;
    private final String tournament_start_date;
    private final String start_time;
    private final String score;
    private final int team1;
    private final int team2;
    private final int winner;
    private final String stage;

    public Match2(int match_number, String tournament_name, String tournament_start_date, String start_time, String score, int team1, int team2, int winner, String stage) {
        this.match_number = match_number;
        this.tournament_name = tournament_name;
        this.tournament_start_date = tournament_start_date;
        this.start_time = start_time;
        this.score = score;
        this.team1 = team1;
        this.team2 = team2;
        this.winner = winner;
        this.stage = stage;
    }
    public int getMatch_number() {
        return match_number;
    }

    public String getTournament_name() {
        return tournament_name;
    }

    public String getTournament_start_date() {
        return tournament_start_date;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getScore() {
        return score;
    }

    public int getTeam1() {
        return team1;
    }

    public int getTeam2() {
        return team2;
    }

    public int getWinner() {
        return winner;
    }

    public String getStage() {
        return stage;
    }
}