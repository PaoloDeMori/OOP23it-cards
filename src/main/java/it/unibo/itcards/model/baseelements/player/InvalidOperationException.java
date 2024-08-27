package it.unibo.itcards.model.baseelements.player;

/**
 * An exception that is thrown if an operation required by an external class is
 * not possible.
 */
public class InvalidOperationException extends Exception {
    public static final long serialVersionUID = 4328744;
    /**
     * Creates an instance of this exception wth a string.
     * 
     * @param message the reason of the exception
     */
    public InvalidOperationException(final String message) {
        super(message);
    }
}
