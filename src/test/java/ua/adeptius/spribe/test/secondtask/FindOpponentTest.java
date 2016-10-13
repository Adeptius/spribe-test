package ua.adeptius.spribe.test.secondtask;


import org.junit.Test;
import ua.adeptius.spribe.test.Exceptions.NoSuchAwaitingPlayerException;
import ua.adeptius.spribe.test.Exceptions.PlayerAlreadyWaitingException;
import ua.adeptius.spribe.test.Exceptions.NoAwaitingPlayersException;

import static org.junit.Assert.*;
import static ua.adeptius.spribe.test.secondtask.FindOpponent.*;

public class FindOpponentTest {

    @Test
    public void getOpponentForPlayerTest() throws Exception {
        Player vasya = new Player("Vasya", 1.0);
        Player petya = new Player("Petya", 2.0);
        Player kolya = new Player("Kolya", 3.0);
        addAwaitingPlayer(vasya);
        addAwaitingPlayer(petya);
        addAwaitingPlayer(kolya);

        Player newPlayer = new Player("New Player", 2.4);
        Player foundedOpponentForNewPlayer = getOpponentForPlayer(newPlayer);

        assertEquals(foundedOpponentForNewPlayer, petya);
    }

    @Test(expected = PlayerAlreadyWaitingException.class)
    public void addPlayerTest() throws Exception {
        addAwaitingPlayer(new Player("Vasya", 1.35));
        addAwaitingPlayer(new Player("Vasya", 1.35));
    }

    @Test(expected = NoSuchAwaitingPlayerException.class)
    public void removePlayerTest() throws Exception {
        removeAwaitingPlayer(new Player("Absent Guy", 5.2));
    }

    @Test(expected = NoAwaitingPlayersException.class)
    public void noWaitingOpponentsTest() throws Exception {
        getOpponentForPlayer(new Player("First Player", 2.4));
    }
}
