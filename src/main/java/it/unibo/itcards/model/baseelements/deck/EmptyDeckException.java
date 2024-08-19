package it.unibo.itcards.model.baseelements.deck;
/**
 * Implementation of an exception that occurs while trying to get cards from a void deck.
*/
public class EmptyDeckException extends RuntimeException {
    private static final long serialVersionUID = 2L;
    /**
    * Constructor for this exception.
    * @param message A string that describes the reasons why the exception is thrown
    */
    public EmptyDeckException(final String message) {
        super(message);
    }
}
