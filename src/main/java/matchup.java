

/**
 * Created by jfgilman on 2/22/17.
 */
public class matchup {

    public int gameid;
    public int topPlayer;
    public int bottomPlayer;
    public String time;
    public int winner;
    public int version;
    public int seed;

    public matchup(int gameid, int topPlayer, int bottomPlayer,
                   String time, int winner, int version, int seed){

        this.gameid = gameid;
        this.topPlayer = topPlayer;
        this.bottomPlayer = bottomPlayer;
        this.time = time;
        this.winner = winner;
        this.version = version;
        this.seed = seed;

    }

}
