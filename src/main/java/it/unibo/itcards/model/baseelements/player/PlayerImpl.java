package it.unibo.itcards.model.baseelements.player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import it.unibo.itcards.commons.Card;

import java.util.List;
import java.util.Optional;

/**
 * This class implements the player interface and represent a human player.
 */

public class PlayerImpl implements Player {

    private final String name;
    private final Set<Card> wonCards;
    private final Hand cards;
    private final int maxNumberOfCards;
    private final List<Card> playedCards;
    private Card selectedCard = null;
    private int points=0;

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    /**
     * Constructor of this class that sets the name and the maximum number of
     * cards that a player can have in their hand.
     * 
     * @param name             the name of the player
     * @param maxNumberOfCards the maximum amount of cards that a player can have
     */
    public PlayerImpl(final String name, final int maxNumberOfCards) {
        this.name = name;
        wonCards = new HashSet<>();
        this.maxNumberOfCards = maxNumberOfCards;
        cards = new Hand(this.maxNumberOfCards);
        this.playedCards = new ArrayList<>();
    }

    /**
     * This method checks if the specified card is playable by the player and if so, returns it.
     * 
     * @param card is the card to remove
     * @throws InvalidOperationException if the specified card is not playable
     * @return the card to play
     */
    @Override
    public Card playCard(final Card card) throws InvalidOperationException {
        if (!cards.remove(card)) {
            throw new InvalidOperationException("You are trying to play an invalid card");
        }
        return card;
    }

    /**
     * Adds a list of cards to the list representing the cards won by
     * the player.
     * 
     * @param cards is a list of cards to add to the wonCards list
     */
    @Override
    public void addWonCards(final Set<Card> cards) {
        this.wonCards.addAll(cards);
    }

    /**
     * Adds a card to the hand of the player.
     * 
     * @return true if the card was added , false otherwise
     */
    @Override
    public boolean drawCard(final Card card) {
        return cards.add(card);
    }

    /**
     * Returns a particular card from the hand of the player at a given position.
     * 
     * @param index is the position of the card
     * @return an Optional containing the card if a card exists at a specified
     *         position, an empty optional otherwise
     */
    @Override
    public Optional<Card> getCard(final int index) {
        if (index <= (cards.size() - 1) && index >= 0) {
            return Optional.of(this.cards.get(index));
        } else {
            return Optional.empty();
        }
    }

    /**
     * Returns the list of cards won by the player.
     * 
     * @return a list of Card representing the cards that the player has won during
     *         a game
     */
    @Override
    public Set<Card> getWonCards() {
        return this.wonCards;
    }

    /**
     * Returns a list of cards that represents the hand of the player.
     * 
     * @return the hand of the player
     */
    @Override
    public List<Card> getCards() {
        return cards;
    }

    /**
     * Returns the name of the player.
     * 
     * @return a String representing the name of the player
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Returns the maximum number of cards in the hand of the player.
     * 
     * @return the variable used to set the max amount of cards available in the
     *         hand of the player.
     */
    @Override
    public int getMaxNumberOfCards() {
        return this.maxNumberOfCards;
    }

    /**
     * Returns the number of cards in the hand of the player.
     * 
     * @return the cards available in the hand of the player.
     */
    @Override
    public int getNumberOfCards() {
        return this.cards.size();
    }

    /**
     * Returns the list of cards played by the player.
     * 
     * @return the list of cards played by the player
     */
    @Override
    public List<Card> getPlayedCards() {
       return this.playedCards;
    }

    /**
     * Adds a card to the list of cards played by the player.
     * 
     * @param card the card to add
     */
    @Override
    public void addPlayedCard(Card card) {
        this.playedCards.add(card);
    }

    @Override
    public String toString() {
        return this.name;
    }
    /**
     * Sets the hand of the player
     * @param hand
     */
    public void setHand(List<Card> hand){
        for(Card card : hand){
            cards.add(card);
        }
    }

    @Override
    public boolean isAi() {
       return false;
    }

    public void selectCard(Card card){
        this.selectedCard=card;
    }

    public Card chooseCard() throws InvalidOperationException{
       return this.playCard(selectedCard);
    }
}
