
import java.util.List;
import java.util.ArrayList;

/**
 * Created by jfgilman on 2/22/17.
 */
public class actions {

    public List<action> actionList = new ArrayList<action>();

    public actions (){

    }

    public class action {
        public int tick;
        public int cardIndex;
        public int x;
        public int y;
    }
}
