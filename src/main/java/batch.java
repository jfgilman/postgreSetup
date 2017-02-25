
import java.util.List;
import java.util.ArrayList;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Will be a list of the objects needed to be uploaded to the database
 */
public class batch {

    public List<game> games = new ArrayList<game>();

    /**
     * Constructor
     */
    public batch(){

    }

    public void addGame(game g){
        this.games.add(g);
    }

    /**
     * uploads batch to database
     */
    public void upload(){

        try {

            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;

        }

        Connection c;
        Statement stmt = null;

        try {
            c = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/saTest", "postgres",
                    "dbpass");

        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        try {

            for(game g : games){

                // adding matchup info
                stmt = c.createStatement();
                String sql = "INSERT INTO MATCHUP (GAMEID, TOPPLAYER, BOTTOMPLAYER, DATE, VERSION, WINNER, SEED) "
                        + "VALUES (" + g.matchup.gameid + "," + g.matchup.topPlayer + "," + g.matchup.bottomPlayer + "," +
                        g.matchup.time + "," + g.matchup.version +","+ g.matchup.winner + ","  + g.matchup.seed + ");";
                stmt.executeUpdate(sql);

                // adding each players deck
                for(String card : g.playerOneDeck.cards){
                    String sql2 = "INSERT INTO DECKS (GAMEID, PLAYERID, CARDNAME) "
                            + "VALUES (" + g.playerOneDeck.gameid + "," + g.playerOneDeck.playerid + ", " + card + ");";
                    stmt.executeUpdate(sql2);
                }

                for(String card : g.playerTwoDeck.cards){
                    String sql2 = "INSERT INTO DECKS (GAMEID, PLAYERID, CARDNAME) "
                            + "VALUES (" + g.playerTwoDeck.gameid + "," + g.playerTwoDeck.playerid + ", " + card + ");";
                    stmt.executeUpdate(sql2);
                }

                // need to update actions
                for(actions.action a : g.playerOneActions.actionList){
                    String sql3 = "INSERT INTO DECKS (TICK, GAMEID, PLAYERID, DROPX, DROPY, CARDINDEX) "
                            + "VALUES (" + a.tick + "," + g.playerOneActions.gameid + "," + g.playerOneActions.playerid + "," +
                            a.x + "," +a.y+ "," + a.cardIndex + ");";
                    stmt.executeUpdate(sql3);
                }

                for(actions.action a : g.playerTwoActions.actionList){
                    String sql3 = "INSERT INTO DECKS (TICK, GAMEID, PLAYERID, DROPX, DROPY, CARDINDEX) "
                            + "VALUES ("+a.tick +"," + g.playerTwoActions.gameid + "," + g.playerTwoActions.playerid + "," +
                            a.x + "," +a.y+ "," + a.cardIndex + ");";
                    stmt.executeUpdate(sql3);
                }

            }

            stmt.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records added successfully");

    }
}
