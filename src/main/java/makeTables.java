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
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }

        try {
            stmt = c.createStatement();
            String sql = "CREATE TABLE PLAYERS " +
                    "(PLAYERID INT PRIMARY KEY     NOT NULL," +
                    " NAME    CHAR(50))";
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("PLAYERS table created");

        try {
            stmt = c.createStatement();
            String sql = "CREATE TABLE MATCHUP " +
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

        } catch (SQLException e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("MATCHUP table created");


        try {
            stmt = c.createStatement();
            String sql = "CREATE TABLE PLAYERACTIONS " +
                    "(PLAYERID INT REFERENCES PLAYERS (PLAYERID)," +
                    " GAMEID   INT REFERENCES MATCHUP (GAMEID), " +
                    " ACTIONSET INT PRIMARY KEY  NOT NULL)";

            stmt.executeUpdate(sql);



        } catch (SQLException e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("PLAYERACTIONS table created");

        try {
            stmt = c.createStatement();
            String sql = "CREATE TABLE ACTIONS " +
                    "(TICK INT PRIMARY KEY, " +
                    " LOCATION INTEGER[], " +
                    " CARDINDEX INT, " +
                    " ACTIONSET INT REFERENCES playeractions (ACTIONSET))";
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("ACTIONS table created");

        try {
            stmt = c.createStatement();
            String sql = "CREATE TABLE CARDS " +
                    "(CARDID INT PRIMARY KEY     NOT NULL," +
                    " NAME    CHAR(50))";
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("CARDS table created");

    }

}