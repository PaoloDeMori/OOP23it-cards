package it.unibo.itcards.model.baseelements.player;

import java.util.ArrayList;

import it.unibo.itcards.commons.Card;

/**
 * A representation of the playable cards of a player.
 */

public class Hand extends ArrayList<Card> {

    private final int maxSize;
    public static final long serialVersionUID = 4328743;

    /**
     * The constructor of this class, that set the field maxSize.
     * 
     * @param maxSize represent the number of cards of a player
     */
    public Hand(final int maxSize) {
        super();
        this.maxSize = maxSize;
    }

    /**
     * This method adds a card to the hand of the player if it is possible to do so.
     * 
     * @param card is the card to add, the card cannot be null
     * @return true if the card is succesfully added, otherwise it returns false
     */
    @Override
    public boolean add(final Card card) {
        if (this.size() >= maxSize || card == null || this.contains(card)) {
            return false;
        } else {
            return super.add(card);
        }
    }

    /**
     * A getter for the field maxSize.
     * 
     * @return the number of card that a player can have in their hand.
     */
    public int getMaxSize() {
        return this.maxSize;
    }

}
