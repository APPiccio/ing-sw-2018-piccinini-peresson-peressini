package PPP;

import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;

public class GameTest {

    @Test
    public static void joiningTest(){

        Game game = new Game();

        String rightName;

        game.joinGame("pippo");
        game.joinGame("pippo");
        game.joinGame("pippo");
        game.joinGame("pippo");

        ArrayList<Player> players = game.getPlayers();

        rightName = players.get(0).getUsername();

        for(int i = 1; i < 4; i++){
            assertEquals("pippo("+ (i) +")", players.get(i).getUsername());
        }



        players.get(0).setUsername("attaccoRepo");
        players = game.getPlayers();

        assertEquals(rightName,players.get(0).getUsername());




    }

}
