package it.unibo.itcards;
import it.unibo.itcards.model.baseelements.deck.Deck;
import it.unibo.itcards.model.baseelements.deck.DeckFactory;
import it.unibo.itcards.model.baseelements.deck.ShuffledDeckFactoryImpl;

/**
 * Main class.
 */
public final class Main {

/**
    * A private constructor because an helper class cannot have a public constructor.
    */

    private Main() {
      throw new UnsupportedOperationException("Utility class");
  }

   /**
    * An example enter point of my program.
    * @param args commands line arguments.
    */
   public static void main(final String[] args) {
    DeckFactory factoryDeck = new ShuffledDeckFactoryImpl();
    Deck deck = factoryDeck.buildDeck();
    System.out.println(deck.toString());
   } 
}
