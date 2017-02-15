import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample {

    public static void main(String[] argv) {

        System.out.println("-------- PostgreSQL "
                + "JDBC Connection Testing ------------");

        try {

            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your PostgreSQL JDBC Driver? "
                    + "Include in your library path!");
            e.printStackTrace();
            return;

        }

        System.out.println("PostgreSQL JDBC Driver Registered!");

        Connection c = null;
        Statement stmt = null;

        try {

            c = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/saTest", "postgres",
                    "dbpass");

        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
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
            String sql = "CREATE TABLE MATCHUP " +
                    "(GAMEID INT PRIMARY KEY     NOT NULL," +
                    " TOPPLAYER      CHAR(50)    NOT NULL, " +
                    " BOTTOMPLAYER    CHAR(50)     NOT NULL, " +
                    " DATE        INT, " +
                    " VERSION    INT, " +
                    " WINNER    CHAR(50), " +
                    " SEED     INT)";
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Table created successfully");

        try {

            stmt = c.createStatement();
            String sql = "INSERT INTO MATCHUP (GAMEID, TOPPLAYER, BOTTOMPLAYER, DATE, VERSION, WINNER, SEED) "
                    + "VALUES (1, 'James', 'Nick M', 5, 1, 'James', 2550 );";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO MATCHUP (GAMEID, TOPPLAYER, BOTTOMPLAYER, DATE, VERSION, WINNER, SEED) "
                    + "VALUES (2, 'James', 'Nick K', 5, 1, 'James', 2550 );";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO MATCHUP (GAMEID, TOPPLAYER, BOTTOMPLAYER, DATE, VERSION, WINNER, SEED) "
                    + "VALUES (3, 'James', 'Maria', 5, 1, 'James', 2550 );";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO MATCHUP (GAMEID, TOPPLAYER, BOTTOMPLAYER, DATE, VERSION, WINNER, SEED) "
                    + "VALUES (4, 'James', 'Eric', 5, 1, 'James', 2550 );";
            stmt.executeUpdate(sql);

            stmt.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
        System.out.println();

        try {

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM MATCHUP;" );
            while ( rs.next() ) {
                int gameid = rs.getInt("gameid");
                String  topplayer = rs.getString("topplayer");
                String  bottomplayer = rs.getString("bottomplayer");
                int  date = rs.getInt("date");
                int  version = rs.getInt("version");
                String winner = rs.getString("winner");
                int  seed = rs.getInt("seed");
                System.out.println( "GAMEID = " + gameid );
                System.out.println( "TOPPLAYER = " + topplayer );
                System.out.println( "BOTTOMPLAYER = " + bottomplayer );
                System.out.println( "DATE = " + date );
                System.out.println( "VERSION = " + version );
                System.out.println( "WINNER = " + winner );
                System.out.println( "SEED = " + seed );
                System.out.println();
        }
        rs.close();
        stmt.close();
        c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
       System.out.println("Operation done successfully");
    }

}