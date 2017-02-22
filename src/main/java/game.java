
import java.util.List;
import java.util.ArrayList;

/**
 * Created by jfgilman on 2/22/17.
 */
public class game {
    public matchup matchup;
    public deck playerOneDeck;
    public deck playerTwoDeck;
    public actions playerOneActions;
    public actions playerTwoActions;

    public game (matchup matchup, deck playerOneDeck, deck playerTwoDeck,
                 actions playerOneActions, actions playerTwoActions){

        this.matchup = matchup;
        this.playerOneActions = playerOneActions;
        this.playerTwoActions = playerTwoActions;
        this.playerOneDeck = playerOneDeck;
        this.playerTwoDeck = playerTwoDeck;

    }

}
