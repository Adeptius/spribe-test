package ua.adeptius.spribe.test.secondtask;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.adeptius.spribe.test.exceptions.NoSuchAwaitingPlayerException;
import ua.adeptius.spribe.test.exceptions.PlayerAlreadyWaitingException;
import ua.adeptius.spribe.test.exceptions.NoAwaitingPlayersException;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class FindOpponentTest {

    private static FindOpponent findOpponent;

    @BeforeClass
    public static void init(){
        findOpponent = new FindOpponent();
    }

    @Before
    public void clearListOfUsers(){
        findOpponent.getAllWaitingPlayers().clear();
    }

    @Test
    public void getOpponentForPlayerTest() throws Exception {
        Player vasya = new Player("Vasya", 1.0);
        Player petya = new Player("Petya", 2.0);
        Player kolya = new Player("Kolya", 3.0);
        findOpponent.addAwaitingPlayer(vasya);
        findOpponent.addAwaitingPlayer(petya);
        findOpponent.addAwaitingPlayer(kolya);

        Player newPlayer = new Player("New Player", 2.4);
        Player foundedOpponentForNewPlayer = findOpponent.getOpponentForPlayer(newPlayer);
        assertNotNull("Opponent is null!", foundedOpponentForNewPlayer);
        assertEquals("Opponent for 2.4 must be 2.0!", foundedOpponentForNewPlayer, petya);

        newPlayer.setRating(2.6);
        foundedOpponentForNewPlayer = findOpponent.getOpponentForPlayer(newPlayer);
        assertNotNull("Opponent is null!", foundedOpponentForNewPlayer);
        assertEquals("Opponent for 2.6 must be 3.0!", foundedOpponentForNewPlayer, kolya);

        assertEquals("Players are not deleting after searhing opponent!",
                findOpponent.countOfWaitingPlayers(), 1);
    }

    @Test
    public void addPlayerToWaitList() throws Exception {
        Player vasya = new Player("Vasya", 1.0);
        Player petya = new Player("Petya", 2.0);
        Player kolya = new Player("Kolya", 3.0);
        findOpponent.addAwaitingPlayer(vasya);
        findOpponent.addAwaitingPlayer(petya);
        findOpponent.addAwaitingPlayer(kolya);

        List<Player> actual = findOpponent.getAllWaitingPlayers();
        List<Player> expected = Arrays.asList(vasya, petya, kolya);

        assertEquals(actual,expected);
    }

    @Test(expected = PlayerAlreadyWaitingException.class)
    public void addPlayerTest() throws Exception {
        Player vasya = new Player("Vasya", 1.35);
        findOpponent.addAwaitingPlayer(vasya);
        //Here adding secont time.
        findOpponent.addAwaitingPlayer(vasya);
    }

    @Test(expected = NoSuchAwaitingPlayerException.class)
    public void removePlayerTest() throws Exception {
        findOpponent.removeAwaitingPlayer(new Player("Absent Guy", 5.2));
    }

    @Test(expected = NoAwaitingPlayersException.class)
    public void noWaitingOpponentsTest() throws Exception {
        findOpponent.getOpponentForPlayer(new Player("First Player", 2.4));
    }
}
