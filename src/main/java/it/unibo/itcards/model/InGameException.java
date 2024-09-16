package it.unibo.itcards.model;

/**
 * An exception that is thrown if the game is not in a valid state.
 */
public class InGameException extends RuntimeException {
    private static final long serialVersionUID = 3L;

    /**
     * Creates an instance of this exception using a string.
     * 
     * @param message the reason of the exception
     */
    public InGameException(final Exception e) {
        super(e.getMessage());
    }

        /**
     * Creates an instance of this exception using a string.
     * 
     * @param message the reason of the exception
     */

    public InGameException(final String message) {
        super(message);
    }
}
