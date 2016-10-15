package ua.adeptius.spribe.test.secondtask;


import ua.adeptius.spribe.test.exceptions.NoSuchAwaitingPlayerException;
import ua.adeptius.spribe.test.exceptions.PlayerAlreadyWaitingException;
import ua.adeptius.spribe.test.exceptions.NoAwaitingPlayersException;

import java.util.ArrayList;
import java.util.List;

public class FindOpponent {

    private ArrayList<Player> awaitingPlayers = new ArrayList<>();

    public void addAwaitingPlayer(Player player) throws PlayerAlreadyWaitingException {
        if (awaitingPlayers.contains(player))
            throw new PlayerAlreadyWaitingException();

        awaitingPlayers.add(player);
    }

    public void removeAwaitingPlayer(Player player) throws NoSuchAwaitingPlayerException {
        if (!awaitingPlayers.contains(player))
            throw new NoSuchAwaitingPlayerException();

        awaitingPlayers.remove(player);
    }

    public Player getOpponentForPlayer(Player player)
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

    private double getDifferenceBetween(Player first, Player second){
        return Math.abs(first.getRating() - second.getRating());
    }

    public int countOfWaitingPlayers(){
        return awaitingPlayers.size();
    }

    public List<Player> getAllWaitingPlayers(){
        return awaitingPlayers;
    }
}
