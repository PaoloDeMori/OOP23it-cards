package it.unibo.itcards.model.baseelements.player;

import it.unibo.itcards.commons.Card;
import it.unibo.itcards.model.Model;

public abstract class AIPlayer extends PlayerImpl {

    public AIPlayer(String name, int maxNumberOfCards) {
        super(name, maxNumberOfCards);
    }

    /**
     * This method set the game environment in wich the aiplayer is playing.
     * The aiplayer implementations will use this to calculate the best card to play
     * 
     * @param game is the game to set,
     */
    public abstract void setGame(Model game);

    @Override
    public boolean isAi(){
        return true;
    }

}
