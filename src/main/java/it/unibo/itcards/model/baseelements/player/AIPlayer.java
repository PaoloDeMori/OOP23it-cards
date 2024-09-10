package it.unibo.itcards.model.baseelements.player;

import it.unibo.itcards.model.Model;

/**
 * This class represents an AI player.
 */
public abstract class AIPlayer extends PlayerImpl {

    /**
     * This method set the name and the max numeber of cards of the AI player.
     *
     * @param name             is the name of the AIplayer,
     * @param maxNumberOfCards is the max number of cards of the AI player
     */
    public AIPlayer(final String name, final int maxNumberOfCards) {
        super(name, maxNumberOfCards);
    }

    /**
     * This method set the game environment in wich the aiplayer is playing.
     * The aiplayer implementations will use this to calculate the best card to play
     * 
     * @param game is the game to set,
     */
    public abstract void setGame(Model game);

    /**
     * Returns true if the player is an AI player, false otherwise.
     * 
     * @return true if the player is an AI player, false otherwise
     */
    @Override
    public boolean isAi() {
        return true;
    }

}
