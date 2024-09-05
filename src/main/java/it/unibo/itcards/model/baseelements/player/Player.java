package it.unibo.itcards.model.baseelements.player;

import java.util.List;
import java.util.Set;

import it.unibo.itcards.commons.Card;

import java.util.Optional;

/**
 * Represents a player of a card game.
 */
public interface Player {

    /**
     * Removes the card specified if possible.
     * 
     * @param card the card to remove.
     * @return the card if it is succesfully removed.
     * @throws InvalidOperationException if the operation required wasn't possible
     */
    Card playCard(Card card) throws InvalidOperationException;

    /**
     * Adds a list of Card to the wonCards of a player.
     * 
     * @param cards the card that the player has won
     */
    void addWonCards(Set<Card> cards);

    /**
     * Receives a card and adds it to the player's cards if possible.
     * 
     * @param card the card to add
     * @return true if it was possible to add the card, false otherwise
     */
    boolean drawCard(Card card);

    /**
     * Returns a list of cards won by the player.
     * 
     * @return a list of cards won by the player.
     */
    Set<Card> getWonCards();

    /**
     * Returns the card at a specific position of the list.
     * 
     * @param index the position of the card
     * @return an optional that contains the card if the index position is valid,
     *         otherwise returns an empty optional
     */
    Optional<Card> getCard(int index);

    /**
     * Returns the hand of the player.
     * 
     * @return a list of cards that represent the playable cards of the player
     */
    List<Card> getCards();

    /**
     * Returns the name of the player.
     * 
     * @return the String variable containing the name of the player
     */
    String getName();

    /**
     * Returns the maximum number of playable cards that a player can have.
     * 
     * @return the int variable containing the maximum number of cards in the hand
     *         of the player.
     */
    int getMaxNumberOfCards();

    /**
     * Returns the number of playable cards that a player have.
     * 
     * @return the number of cards in the hand of the player.
     */
    int getNumberOfCards();

    /**
     * Returns the list of cards played by the player in the current game.
     * 
     * @return the list of cards played by the player
     */
    List<Card> getPlayedCards();

    /**
     * Adds a card to the list of cards played by the player.
     * 
     * @param card the card to add
     */
    void addPlayedCard(Card card);

    boolean isAi();

    Card chooseCard()  throws InvalidOperationException;

    public void selectCard(Card card);

    public void setPoints(int points);

    public int getPoints();

}
