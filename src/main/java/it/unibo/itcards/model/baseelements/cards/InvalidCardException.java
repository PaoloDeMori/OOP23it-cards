package it.unibo.itcards.model.baseelements.cards;

/**
 * An exception that is thrown if a card is being created with invalid fields.
 */
public class InvalidCardException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * Creates an instance of this exception wth a string.
     * 
     * @param message the reason of the exception
     */
    public InvalidCardException(final String message) {
        super(message);
    }
}
