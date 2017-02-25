import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by jfgilman on 2/25/17.
 */
public class DBClass {

    static final String DBUrl = System.getProperty("jdbc:postgresql://localhost:5432/saTest");
    static final String DBUser = System.getProperty("postgres");
    static final String DBPassword = System.getProperty("dbpass");

    Connection c = null;
    Statement stmt = null;

    batch b = new batch();

    Timer timer = new Timer();

    public DBClass() {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver?");
            e.printStackTrace();
            return;
        }

        try {
            c = DriverManager.getConnection(DBUrl, DBUser, DBPassword);

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

    }

    public void createTables(){

        try {
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS PLAYERS " +
                    "(PLAYERID INT PRIMARY KEY     NOT NULL," +
                    " NAME    CHAR(50)," +
                    " GAMECOUNT  INT)";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Table Creation Failed!");
            e.printStackTrace();
            return;
        }
        System.out.println("PLAYERS table created");

        try {
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS MATCHUP " +
                    "(GAMEID INT PRIMARY KEY     NOT NULL ," +
                    " TOPPLAYER      INT REFERENCES PLAYERS (PLAYERID), " +
                    " BOTTOMPLAYER    INT REFERENCES PLAYERS (PLAYERID), " +
                    " GAMEDATE        TIMESTAMP , " +
                    " VERSION    INT, " +
                    " WINNER    INT REFERENCES PLAYERS (PLAYERID), " +
                    " SEED     INT)";
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
                    "(TICK INT, " +
                    " PLAYERID INT REFERENCES PLAYERS (PLAYERID)," +
                    " GAMEID   INT REFERENCES MATCHUP (GAMEID), " +
                    " DROPX INT, " +
                    " DROPY INT, " +
                    " CARDINDEX INT," +
                    " PRIMARY KEY (PLAYERID, GAMEID, TICK))";
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
            String sql = "CREATE TABLE IF NOT EXISTS DECKS " +
                    "(PLAYERID INT REFERENCES PLAYERS (PLAYERID)," +
                    " GAMEID   INT REFERENCES MATCHUP (GAMEID)," +
                    " CARDNAME   CHAR(20))";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Table Creation Failed");
            e.printStackTrace();
            return;
        }
        System.out.println("Decks table created");
    }

    public void addGameRecord(game g){
        b.games.add(g);
    }

    public void uploadBatch(){
        b.upload();
        b.games.clear();
    }

    public void startTimer(){

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                uploadBatch();
            }
        }, 30*60*1000, 30*60*1000);

    }
}
