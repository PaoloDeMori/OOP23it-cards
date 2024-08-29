package it.unibo.itcards.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import it.unibo.itcards.model.baseelements.cards.Card;
import it.unibo.itcards.model.baseelements.deck.Deck;
import it.unibo.itcards.model.baseelements.player.Player;
import it.unibo.itcards.model.briscola.BriscolaHelper;

public class BriscolaImpl extends Model {

    private static final int NUMBER_OF_PLAYERS = 2;
    private Card briscola;
    private List<Card> playedCards;

    /**
     * Constructor which initializes the BriscolaImpl object
     * with an empty ArrayList of playedCards.
     */
    public BriscolaImpl() {
        super();
        this.playedCards = new ArrayList<>();
    }

    /**
     * Returns the list of cards currently on the table.
     * This includes the Briscola card followed by the cards played by the players.
     * 
     * @return a list of cards on the table.
     */
    @Override
    public List<Card> getCardsOnTable() {
        final List<Card> cardsOnTable = new ArrayList<>();
        cardsOnTable.add(0, briscola);
        cardsOnTable.addAll(playedCards);
        return cardsOnTable;
    }

    @Override
    public boolean isGameOver() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void playTurn(Card card, Player player) {
        // TODO Auto-generated method stub

    }

    /**
     * Calculates the total points for the specified player.
     * 
     * @param player the player whose points are to be calculated.
     * @return the total points of the player.
     */
    @Override
    public int points(Player player) {
        Set<Card> cards = player.getWonCards();
        int points = 0;
        for (Card card : cards) {
            points += BriscolaHelper.getCardValue(card);
        }
        return points;
    }

    /**
     * Starts the game by drawing the first card from the deck and setting it as the
     * Briscola.
     * Throws an exception if the deck is empty.
     */
    @Override
    public void start() {
        Optional<Card> card = this.deck.drawCard();
        if (card.isPresent()) {
            setBriscola(card.get());
        } else {
            throw new InGameException("Deck is empty");
        }

    }

    /**
     * Determines the winner of the current hand based on the cards played and the
     * Briscola.
     * 
     * @return the player who won the hand.
     * @throws InGameException if no winner can be determined.
     */
    @Override
    public Player winner() {
        if (BriscolaHelper.isWinner(playedCards.get(0), playedCards.get(1), briscola)) {
            for (var player : players) {
                if (player.getPlayedCards().contains(playedCards.get(0))) {
                    return player;

                }
            }
        }
        for (var player : players) {
            if (player.getPlayedCards().contains(playedCards.get(1))) {
                return player;
            }
        }
        throw new InGameException("No winner");
    }

    /**
     * Deals cards to the players. If the deck is empty or there are not enough
     * cards, returns false.
     * 
     * @return true if cards were successfully dealt, false otherwise.
     * @throws InGameException if an invalid card is drawn or if there are not
     *                         enough cards to deal.
     */
    @Override
    public boolean giveCards() {

        Optional<Card> card;

        if (deck.isVoid()) {
            return false;
        }
        if (this.deck.numberOfCards() == 1) {
            card = deck.drawCard();
            if (!card.isPresent()) {
                throw new InGameException("Invalid card in the deck");
            }
            players.get(0).drawCard(card.get());
            players.get(1).drawCard(briscola);
            return true;
        }
        if ((this.deck.numberOfCards() + 1) % NUMBER_OF_PLAYERS != 0) {
            throw new InGameException("Not enough cards to give");
        }
        for (var player : players) {
            card = deck.drawCard();
            if (!card.isPresent()) {
                throw new InGameException("Invalid card in the deck");
            }
            player.drawCard(card.get());
        }
        return true;
    }

    /**
     * Sets the Briscola card for the game.
     * 
     * @param briscolaCard the card to be set as the Briscola.
     */
    public void setBriscola(Card briscolCard) {
        this.briscola = briscolCard;
    }

    /**
     * Returns the Briscola card of the game.
     */
    public Card getBriscola() {
        return briscola;
    }

}
