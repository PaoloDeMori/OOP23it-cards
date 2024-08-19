package it.unibo.itcards.model.baseelements.deck;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.unibo.itcards.model.baseelements.cards.Card;
import it.unibo.itcards.model.baseelements.cards.CardFactory;
import it.unibo.itcards.model.baseelements.cards.CardFactoryImpl;
import it.unibo.itcards.model.baseelements.cards.Suit;

/**
 * Implements the  Deck interface, representing a deck of 40 traditional Italian playing cards.
 */
public class DeckImpl implements Deck {
    private final List<Card> deckList;

    /**
     * Creates a new Istance of this class.
     */
    protected DeckImpl() {
        deckList = new ArrayList<>();
    }

    /**
     * Initializes the deck using a foreach loop and a for loop.
     */
    @Override
    public void init() {
        final CardFactory factory = new CardFactoryImpl();
        for (final Suit suit : Suit.values()) {
            for (int i = 1; i <= Card.MAX_NUMERICAL_VALUE; i++) {
                deckList.add(factory.buildCard(suit, i));
            }
        }
    }

    /**
     * Shuffles the deck using the java.util.Collections helper class.
     */
    @Override
    public void shuffle() {
        Collections.shuffle(deckList);
    }
    /**
     * Returns the card on top of the deck and removes it.
     * @throws EmptyDeckException if the deck is empty
     * @return the first card of the deck
     */
    @Override
    public Card drawCard() throws EmptyDeckException {
        if (!this.isVoid()) {
            return deckList.remove(0);
        }
        throw new EmptyDeckException("The Deck is Empty");
    }

    /**
     * Checks if the deck ended.
     * @return true if the deck is empty, false if it is not
     */
    @Override
    public boolean isVoid() {
        return deckList.isEmpty();
    }

    /**
     * Counts the number of cards in the deck.
     * @return the number of cards in the deck
     */
    @Override
    public int numberOfCards() {
        return this.deckList.size();
    }
    /**
     * Return a string representation of the deck.
     * @return a string representation of the deck
     */
    @Override
    public String toString() {
        final String numberOfCards = "This deck has " + Integer.toString(this.numberOfCards()) + " cards: \n";
        String cards = "";
        for (final Card card: deckList) {
            cards = cards.concat(card.toString().concat(" "));
        }
        return numberOfCards + cards;
    }
}
