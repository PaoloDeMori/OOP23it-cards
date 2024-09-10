package it.unibo.itcards.view.baseelements;

import java.awt.Dimension;

/**
 * Represents the suits of traditional Italian playing cards with their
 * traditional names.
 */
public enum Dim {
    /**
     * Represents the suit of BASTONI.
     */
    SMALL(new Dimension(1280, 720)),
    /**
     * Represents the suit of COPPE.
     */
    MEDIUM(new Dimension(1600, 900)),
    /**
     * Represents the suit of SPADE.
     */
    LARGE(new Dimension(1920, 1080));

    private final Dimension d;

    Dim(final Dimension d) {
        this.d = d;
    }

    /**
     * Returns the dimension associated with the current Dim instance.
     *
     * @return the dimension
     */
    public Dimension getDimension() {
        return this.d;
    }
}
