
import java.util.List;
import java.util.ArrayList;

/**
 * Created by jfgilman on 2/22/17.
 */
public class deck {

    int gameid;
    int playerid;
    public List<String> cards;

    public deck(int gameid, int playerid, List<String> cards){

        this.gameid = gameid;
        this.playerid = playerid;
        this.cards = cards;

    }
}
