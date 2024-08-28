package it.unibo.itcards.model;

public class InGameException extends RuntimeException{
    private static final long serialVersionUID = 3L;
    /**
     * Creates an instance of this exception using a string.
     * 
     * @param message the reason of the exception
     */
    public InGameException(final String message) {
        super(message);
    }
}
