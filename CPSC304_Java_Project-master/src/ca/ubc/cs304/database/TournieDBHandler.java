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

    public ArrayList<String> getTables() throws SQLException{
        try {
            String query = "SELECT table_name FROM user_tables";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);

            ResultSet rs = ps.executeQuery();

            ArrayList<String> table_names = new ArrayList<>();

            while(rs.next()){
                table_names.add(rs.getString("table_name"));
            }

            connection.commit();
            rs.close();
            ps.close();
            return table_names;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
            throw e;
        }
    }

    public ArrayList<String> getTableColumns(String table) throws SQLException{
        try {
            String query = "SELECT column_name FROM USER_TAB_COLUMNS WHERE table_name = ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, table);
            ResultSet rs = ps.executeQuery();

            ArrayList<String> res = new ArrayList<>();

            while(rs.next()){
                res.add(rs.getString("column_name"));
            }

            connection.commit();
            rs.close();
            ps.close();
            return res;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
            throw e;
        }
    }

    public ArrayList<ArrayList<String>> project(String table, ArrayList<String> columns) throws SQLException{
        try {
            String join_cols = String.join(", ", columns);
            String query = "SELECT " + join_cols +  " FROM " + table;
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);

            ResultSet rs = ps.executeQuery();

            ArrayList<ArrayList<String>> res = new ArrayList<>();

            while(rs.next()){
                ArrayList<String> row = new ArrayList<>();
                for (int i = 1; i < columns.size() + 1; i ++){
                    row.add(rs.getObject(i).toString());
                }
                res.add(row);
            }

            connection.commit();
            rs.close();
            ps.close();
            return res;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
            throw e;
        }
    }

    public ArrayList<ArrayList<String>> join(String country) throws SQLException{
        try {
            String query = "SELECT Name, t1.Organization FROM Team1 t1, Team2 t2 WHERE t1.Organization = t2.Organization AND t1.country = ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, country);
            ResultSet rs = ps.executeQuery();

            ArrayList<ArrayList<String>> res = new ArrayList<>();

            if (rs == null){
                System.out.println("null");
            }

            while(rs.next()){
                ArrayList<String> row = new ArrayList<>();
                for (int i = 1; i < 3; i ++){
                    row.add(rs.getObject(i).toString());
                }
                res.add(row);
            }

            connection.commit();
            rs.close();
            ps.close();
            return res;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
            throw e;
        }
    }

    public ArrayList<ArrayList<String>> aggGroupBy() throws SQLException{
        try {
            String query = "SELECT Team_ID, AVG(ranking) AS Average_Team_Ranking FROM Player GROUP BY Team_ID";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            ArrayList<ArrayList<String>> res = new ArrayList<>();

            if (rs == null){
                System.out.println("null");
            }

            while(rs.next()){
                ArrayList<String> row = new ArrayList<>();
                for (int i = 1; i < 3; i ++){
                    row.add(rs.getObject(i).toString());
                }
                res.add(row);
            }

            connection.commit();
            rs.close();
            ps.close();
            return res;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
            throw e;
        }
    }

    public ArrayList<ArrayList<String>> aggGroupByHaving() throws SQLException{
        try {
            String query = "SELECT v.City, AVG(Capacity) AS avg_capacity " +
                    "FROM Tournament t, Venue v " +
                    "WHERE t.Venue_name = v.Name AND t.Venue_city = v.City " +
                    "GROUP BY v.City " +
                    "HAVING COUNT(*) < 2";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            ArrayList<ArrayList<String>> res = new ArrayList<>();

            if (rs == null){
                System.out.println("null");
            }

            while(rs.next()){
                ArrayList<String> row = new ArrayList<>();
                for (int i = 1; i < 3; i ++){
                    row.add(rs.getObject(i).toString());
                }
                res.add(row);
            }

            connection.commit();
            rs.close();
            ps.close();
            return res;
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
            ArrayList<String> tables = db.getTables();
            String table = tables.get(0);
            System.out.println(db.getTables());
            ArrayList<String> columns = db.getTableColumns(table);
            System.out.println(columns);
//            ArrayList<String> cs = new ArrayList<>();
//            cs.add("NAME");
//            cs.add("CAPACITY");
//            cs.add("COUNTRY");
            ArrayList<ArrayList<String>> results = db.project(table, columns);

            for (ArrayList<String> list : results) {
                System.out.println(String.join(", ", list));
            }
            ArrayList<ArrayList<String>> joined = db.join("Canada");
            for (ArrayList<String> list : joined) {
                System.out.println(String.join(", ", list));
            }
            ArrayList<ArrayList<String>> groupBy = db.aggGroupBy();
            for (ArrayList<String> list : groupBy) {
                System.out.println(String.join(", ", list));
            }
            ArrayList<ArrayList<String>> having = db.aggGroupByHaving();
            for (ArrayList<String> list : having) {
                System.out.println(String.join(", ", list));
            }
        } catch (SQLException e) {
            System.out.println("sql exception :)");
        }
        db.close();
    }

}
