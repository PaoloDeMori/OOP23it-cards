package it.unibo.itcards.model.baseelements.deck;
/**
 * Implementation of an exception that occurs while initializing a deck.
*/
public class EmptyDeckException extends Exception {
    /**
    * Constructor for this exception.
    * @param message A string that describes the reasons why the exception is thrown
    */
    public EmptyDeckException(final String message) {
        super(message);
    }
}
