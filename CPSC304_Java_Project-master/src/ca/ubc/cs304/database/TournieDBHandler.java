package ca.ubc.cs304.database;

import java.sql.*;
import java.util.ArrayList;

import ca.ubc.cs304.model.*;

import ca.ubc.cs304.ui.DatabaseGUI;
import ca.ubc.cs304.util.PrintablePreparedStatement;

import java.util.Calendar;
import java.sql.Date;

public class TournieDBHandler {
    // Use this version of the ORACLE_URL if you are running the code off of the server
    //	private static final String ORACLE_URL = "jdbc:oracle:thin:@dbhost.students.cs.ubc.ca:1522:stu";
    // Use this version of the ORACLE_URL if you are tunneling into the undergrad servers
    private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";

    private Connection connection = null;

    public TournieDBHandler() {
        try {
            // Load the Oracle JDBC driver
            // Note that the path could change for new drivers
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public void insertPlayer(Player player) throws SQLException{
        try {
            String query = "INSERT INTO Player VALUES (?,?,?,?,?,?,?)";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setInt(1, player.getId());
            ps.setString(2, player.getFirst_name());
            ps.setString(3, player.getLast_name());
            ps.setString(4, player.getCountry());
            ps.setDate(5, player.getJoin_date());
            ps.setInt(6, player.getRanking());
            ps.setInt(7, player.getTeam_id());

            ps.executeUpdate();
            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
            throw e;
        }
    }

    public void updatePlayerLastName(int pid, String lastName) throws SQLException{
        try {
            String query = "UPDATE Player SET Last_name = ? WHERE ID = ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, lastName);
            ps.setInt(2, pid);

            ps.executeUpdate();
            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
            throw e;
        }
    }

    public void updatePlayerCountry(int pid, String country) throws SQLException{
        try {
            String query = "UPDATE Player SET Country = ? WHERE ID = ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, country);
            ps.setInt(2, pid);

            ps.executeUpdate();
            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
            throw e;
        }
    }

    public void updatePlayerJoinDate(int pid, Date joinDate) throws SQLException{
        try {
            String query = "UPDATE Player SET Join_date = ? WHERE ID = ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setDate(1, joinDate);
            ps.setInt(2, pid);

            ps.executeUpdate();
            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
            throw e;
        }
    }

    public void updatePlayerRanking(int pid, int ranking) throws SQLException{
        try {
            String query = "UPDATE Player SET Ranking = ? WHERE ID = ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setInt(1, ranking);
            ps.setInt(2, pid);

            ps.executeUpdate();
            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
            throw e;
        }
    }

    public void updatePlayerTeamId(int pid, int teamId) throws SQLException{
        try {
            String query = "UPDATE Player SET Team_ID = ? WHERE ID = ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setInt(1, teamId);
            ps.setInt(2, pid);

            ps.executeUpdate();
            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
            throw e;
        }
    }
    public void updatePlayerFirstName(int pid, String firstName) throws SQLException{
        try {
            String query = "UPDATE Player SET First_name = ? WHERE ID = ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, firstName);
            ps.setInt(2, pid);

            ps.executeUpdate();
            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
            throw e;
        }
    }

    public void deletePlayer(int pid) throws SQLException{
        try {
            String query = "DELETE FROM player WHERE id = ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setInt(1, pid);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + " Player " + pid + " does not exist!");
            }

            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
            throw e;
        }
    }

    private void rollbackConnection() {
        try  {
            connection.rollback();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public void login(String username, String password) throws SQLException{
        try {
            if (connection != null) {
                connection.close();
            }

            connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);

            System.out.println("\nConnected to Oracle!");
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            throw e;
        }
    }

    public static void main(String[] args) {
        TournieDBHandler db =  new TournieDBHandler();

        try {
            db.login("ora_ethanz01", "a67073387");
            db.insertPlayer(new Player(1, "test", "test", "antarctica", new Date(System.currentTimeMillis()), 999, 101));
            db.updatePlayerFirstName(1, "testUpdate");
            db.updatePlayerLastName(1, "testUpdate2");
            db.updatePlayerCountry(1, "country?");
            db.updatePlayerRanking(1, 222222);
            db.updatePlayerJoinDate(1, new Date(System.currentTimeMillis()));
            db.updatePlayerTeamId(1, 102);
            db.deletePlayer(1);
        } catch (SQLException e) {
            System.out.println("sql exception :)");
        }
    }

}
