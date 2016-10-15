package ua.adeptius.spribe.test.secondtask;


import ua.adeptius.spribe.test.exceptions.NoSuchAwaitingPlayerException;
import ua.adeptius.spribe.test.exceptions.PlayerAlreadyWaitingException;
import ua.adeptius.spribe.test.exceptions.NoAwaitingPlayersException;

import java.util.ArrayList;

public class FindOpponent {

    private static ArrayList<Player> awaitingPlayers = new ArrayList<>();

    public static void addAwaitingPlayer(Player player) throws PlayerAlreadyWaitingException {
        if (awaitingPlayers.contains(player))
            throw new PlayerAlreadyWaitingException();

        awaitingPlayers.add(player);
    }

    public static void removeAwaitingPlayer(Player player) throws NoSuchAwaitingPlayerException {
        if (!awaitingPlayers.contains(player))
            throw new NoSuchAwaitingPlayerException();

        awaitingPlayers.remove(player);
    }

    public static Player getOpponentForPlayer(Player player)
            throws NoAwaitingPlayersException, NoSuchAwaitingPlayerException {
        if (awaitingPlayers.isEmpty())
            throw new NoAwaitingPlayersException();

        Player opponent = awaitingPlayers.get(0);
        double difference = getDifferenceBetween(player, awaitingPlayers.get(0));

        for (int i = 1; i < awaitingPlayers.size(); i++) {
            Player currentWaitingPlayer = awaitingPlayers.get(i);
            double currentDifference = getDifferenceBetween(player, currentWaitingPlayer);
            if (currentDifference < difference){
                opponent = currentWaitingPlayer;
                difference = currentDifference;
            }
        }
        removeAwaitingPlayer(opponent);
        return opponent;
    }

    private static double getDifferenceBetween(Player first, Player second){
        return Math.abs(first.getRating() - second.getRating());
    }

    public static int countOfWaitingPlayers(){
        return awaitingPlayers.size();
    }

}
