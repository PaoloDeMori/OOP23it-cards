package it.unibo.itcards.model;

import java.util.Iterator;
import java.util.List;

import it.unibo.itcards.model.baseelements.player.Player;

public class PlayerIterator implements Iterator<Player> {

    List<Player> players;
    int current = 0;
    Player winnerPlayer;
    boolean hasplayed = false;

    public PlayerIterator(List<Player> players) {
        this.players = players;
    }

    @Override
    public boolean hasNext() {
        return current+1!=players.indexOf(winnerPlayer);
    }

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

    public void setWinnerPlayer(Player winnerPlayer) {
        this.winnerPlayer = winnerPlayer;
        current = players.indexOf(winnerPlayer);
    }

    public class ExcessiveIterationException extends RuntimeException{

        public ExcessiveIterationException(String message){
            super(message);
        }
    }
}
