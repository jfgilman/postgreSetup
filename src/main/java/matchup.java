
import java.security.Timestamp;
import java.util.Date;

/**
 * Created by jfgilman on 2/22/17.
 */
public class matchup {

    public int gameid;
    public int topPlayer;
    public int bottomPlayer;
    public Timestamp time;
    public int winner;
    public int version;
    public int seed;

    public matchup(int gameid, int topPlayer, int bottomPlayer,
                   Timestamp time, int winner, int version, int seed){

        this.gameid = gameid;
        this.topPlayer = topPlayer;
        this.bottomPlayer = bottomPlayer;
        this.time = time;
        this.winner = winner;
        this.version = version;
        this.seed = seed;

    }

}
