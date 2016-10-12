package ua.adeptius.spribe.test.secondtask;


import ua.adeptius.spribe.test.Exceptions.NoSuchAwaitingPlayer;
import ua.adeptius.spribe.test.Exceptions.PlayerAlreadyWaitingException;
import ua.adeptius.spribe.test.Exceptions.ThereIsNoAwaitingPlayers;

import java.util.ArrayList;

public class FindOpponent {

    private static ArrayList<Player> awaitingPlayers = new ArrayList<>();

    public static void addAwaitingPlayer(Player player){
        if (awaitingPlayers.contains(player))
            throw new PlayerAlreadyWaitingException();

        awaitingPlayers.add(player);
    }

    public static void removeAwaitingPlayer(Player player){
        if (!awaitingPlayers.contains(player))
            throw new NoSuchAwaitingPlayer();
        awaitingPlayers.remove(player);

    }

    public static Player getOpponentForPlayer(Player player){
        if (awaitingPlayers.isEmpty())
            throw new ThereIsNoAwaitingPlayers();

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

}
