
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Arrays;


/**
 * will create some games and try and enter them into the database
 */
public class testRun {
    public static void main(String[] args) {

        batch b = new batch();

        int gameid = 2;
        String time1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        int topPlayer = 1;
        int bottomPlayer = 2;
        int winner = 1;
        int version = 1;
        int seed = 2558;
        List<String> tpCards = Arrays.asList("Cupcake", "Hotwings", "Slurpee",
                "GumballCannon", "MarshmallowMan", "GummyWorms", "CanOfWorms", "Toothpicks");
        List<String> bpCards = Arrays.asList("Cupcake", "Hotwings", "Slurpee",
                "GumballCannon", "MarshmallowMan", "GummyWorms", "CanOfWorms", "Toothpicks");

        actions.action tpa1 = new actions.action(210, 4, 100, 10000);
        actions.action tpa2 = new actions.action(710, 2, 100, 90000);
        actions.action tpa3 = new actions.action(3010, 3, 2000, 150000);
        actions.action tpa4 = new actions.action(10610, 1, 80000, 110000);

        actions.action bpa1 = new actions.action(10, 1, 100, 200);
        actions.action bpa2 = new actions.action(510, 2, 100, 1000);
        actions.action bpa3 = new actions.action(1010, 3, 2000, 2000);
        actions.action bpa4 = new actions.action(10010, 4, 80000, 70000);

        actions tpAction = new actions(topPlayer, gameid);
        actions bpAction = new actions(bottomPlayer, gameid);

        tpAction.addAction(tpa1);
        tpAction.addAction(tpa2);
        tpAction.addAction(tpa3);
        tpAction.addAction(tpa4);

        bpAction.addAction(bpa1);
        bpAction.addAction(bpa2);
        bpAction.addAction(bpa3);
        bpAction.addAction(bpa4);

        deck tpDeck = new deck(gameid, topPlayer, tpCards);
        deck bpDeck = new deck(gameid, bottomPlayer, bpCards);
        matchup m1 = new matchup(gameid, topPlayer, bottomPlayer, time1, winner, version, seed);

        game g1 = new game(m1, tpDeck, bpDeck, tpAction, bpAction);

        b.games.add(g1);
        b.upload();
    }
}