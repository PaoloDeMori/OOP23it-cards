package it.unibo.itcards.model.baseelements.player;

import it.unibo.itcards.commons.Card;
import it.unibo.itcards.model.Model;

public interface AIPlayer extends Player {

    /**
     * This method set the game environment in wich the aiplayer is playing.
     * The aiplayer implementations will use this to calculate the best card to play
     * 
     * @param game is the game to set,
     */
    void setGame(Model game);

    /**
     * This method calculate the best card to play and returns it.
     * 
     * @return an instance of the class card that represents the best card to play.
     */
    Card chooseCard();

}
