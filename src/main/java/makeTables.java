import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class makeTables {

    public static void main(String[] argv) {

        try {

            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {

            System.out.println("JDBC Driver?");
            e.printStackTrace();
            return;
        }

        Connection c = null;
        Statement stmt = null;

        try {
            c = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/saTest", "postgres",
                    "dbpass");

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }

        if (c != null) {
            System.out.println("Connected to database");
        } else {
            System.out.println("Failed to make connection!");
        }

        try {
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS PLAYERS " +
                    "(PLAYERID INT PRIMARY KEY     NOT NULL," +
                    " NAME    CHAR(50)," +
                    " GAMECOUNT  INT)";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Table Creation Failed");
            e.printStackTrace();
            return;
        }
        System.out.println("PLAYERS table created");

        try {
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS MATCHUP " +
                    "(GAMEID INT PRIMARY KEY     NOT NULL," +
                    " TOPPLAYER      INT REFERENCES PLAYERS (PLAYERID), " +
                    " BOTTOMPLAYER    INT REFERENCES PLAYERS (PLAYERID), " +
                    " TIME        TIMESTAMP , " +
                    " VERSION    INT, " +
                    " WINNER    INT REFERENCES PLAYERS (PLAYERID), " +
                    " SEED     INT, " +
                    " TOPDECK    INTEGER[], " +
                    " BOTTOMDECK    integer[])";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Table Creation Failed");
            e.printStackTrace();
            return;
        }
        System.out.println("MATCHUP table created");

        try {
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS ACTIONS " +
                    "(TICK INT PRIMARY KEY, " +
                    " PLAYERID INT REFERENCES PLAYERS (PLAYERID)," +
                    " GAMEID   INT REFERENCES MATCHUP (GAMEID), " +
                    " DROPX INT, " +
                    " DROPY INT, " +
                    " CARDINDEX INT)";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Table Creation Failed");
            e.printStackTrace();
            return;
        }
        System.out.println("ACTIONS table created");

        try {
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS CARDS " +
                    "(CARDID INT PRIMARY KEY     NOT NULL," +
                    " NAME    CHAR(50))";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Table Creation Failed");
            e.printStackTrace();
            return;
        }
        System.out.println("CARDS table created");


        try {
            stmt = c.createStatement();
            String sql = "INSERT INTO PLAYERS (PLAYERID, NAME, GAMECOUNT) "
                    + "VALUES (1, 'James G', 0);";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO PLAYERS (PLAYERID, NAME, GAMECOUNT) "
                    + "VALUES (2, 'Nick M', 0);";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO PLAYERS (PLAYERID, NAME, GAMECOUNT) "
                    + "VALUES (3, 'Maria J', 0);";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO PLAYERS (PLAYERID, NAME, GAMECOUNT) "
                    + "VALUES (4, 'Nick K', 0);";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO PLAYERS (PLAYERID, NAME, GAMECOUNT) "
                    + "VALUES (5, 'Eric R', 0);";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO PLAYERS (PLAYERID, NAME, GAMECOUNT) "
                    + "VALUES (6, 'Tea B', 0);";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO PLAYERS (PLAYERID, NAME, GAMECOUNT) "
                    + "VALUES (7, 'Lisa W', 0);";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO PLAYERS (PLAYERID, NAME, GAMECOUNT) "
                    + "VALUES (8, 'Eric L', 0);";
            stmt.executeUpdate(sql);

            stmt.close();
        } catch (SQLException e) {
            System.out.println("Data Entry Failed");
            e.printStackTrace();
            return;
        }

        System.out.println("Players Entered");

    }

}