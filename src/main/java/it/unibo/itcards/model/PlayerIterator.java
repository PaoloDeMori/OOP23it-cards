package it.unibo.itcards.model;

import java.util.Iterator;
import java.util.List;

import it.unibo.itcards.model.baseelements.player.Player;

/**
 * An iterator that cycles through a list of players, resetting to the winner
 * when
 * the turn ends.
 * This iterator manages the current turn in the game and keeps track of the
 * player
 * who won the last round.
 */

public class PlayerIterator implements Iterator<Player> {

    List<Player> players;
    int current = 0;
    Player winnerPlayer;
    boolean hasplayed = false;

    /**
     * Construct a PlayerIterator with a list of players.
     * 
     * @param players the list of players that the iterator will cycle through.
     */
    public PlayerIterator(List<Player> players) {
        this.players = players;
    }

    /**
     * Checks if there are more players before reaching the winner of the last
     * round.
     * cause the winner does not have the next one.
     * 
     * @return {@code true} if the current player has not yet reached the winner,
     *         {@code false} otherwise
     */
    @Override
    public boolean hasNext() {
        return current+1!=players.indexOf(winnerPlayer);
    }

    /**
     * Moves to the next player in the list. If the iterator has reached the end of
     * the list,
     * it will cycle back to the first player.
     * 
     * @return the next player in the sequence.
     * @throw ExcessiveIterationException if the method is called when there are no
     *        more players to iterate
     *        over(when the round is ended).
     */
    @Override
    public Player next() {
        if(!hasNext()){
            throw new ExcessiveIterationException("No next player");
        }
        if (current+1 == players.size()) {
            current = 0;
        } else {
            current++;
        }
        return players.get(current);

    }

    /**
     * Sets the winner of the last round and updates
     * the iterator to start from the winner.
     * 
     * @param winnerPlayer the player who won the last round
     */
    public void setWinnerPlayer(Player winnerPlayer) {
        this.winnerPlayer = winnerPlayer;
        current = players.indexOf(winnerPlayer);
    }

    /**
     * Exception thrown when the iterator tries to move
     * beyond the available players in the current round
     */
    public class ExcessiveIterationException extends RuntimeException {

        /**
         * Constructs a new ExcessiveIterationException with the specified detail
         * message
         * 
         * @param message the detail message
         */
        public ExcessiveIterationException(String message) {
            super(message);
        }
    }
}
