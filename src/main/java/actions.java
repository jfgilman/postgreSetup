
import java.util.List;
import java.util.ArrayList;

/**
 * Created by jfgilman on 2/22/17.
 */
public class actions {

    public List<action> actionList = new ArrayList<action>();
    public int playerid;
    public int gameid;

    public actions (int playerid, int gameid){
        this.playerid = playerid;
        this.gameid = gameid;
    }

    public void addAction(action a){
        actionList.add(a);
    }

    public class action {
        public int tick;
        public int cardIndex;
        public int x;
        public int y;

        public action(int tick, int cardIndex, int x, int y){
            this.tick = tick;
            this.cardIndex = cardIndex;
            this.x = x;
            this.y = y;
        }

    }
}
