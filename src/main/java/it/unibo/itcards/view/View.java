package it.unibo.itcards.view;

import java.awt.Color;
import java.awt.Font;

import it.unibo.itcards.commons.Observer;

/**
 * Interface representing the view component of the application.
 * This interface defines methods to control the game's UI and interaction
 * between the player and the AI.
 */
public interface View extends Observer {

    /** The color used for invisible components. */
    Color INVISIBLE_COLOR = new Color(0, 0, 0, 0);

    /** The color representing the opponent in the game. */
    Color OPPONENT_COLOR = new Color(255, 0, 0);

    /** The color representing the player in the game. */
    Color PLAYER_COLOR = new Color(255, 217, 46);

    /** The standard horizontal gap for component layout. */
    int STANDARD_HGAP = 10;

    /** The standard vertical gap for component layout. */
    int STANDARD_VGAP = 5;

    /** The standard font used in the game for UI components. */
    Font STANDARD_FONT = new Font("Arial", Font.BOLD, 40);

    /**
     * Displays the game window or UI.
     */
    void show();

    /**
     * Starts the game view and initializes the components.
     */
    void start();

    /**
     * Stops the game view and performs cleanup.
     */
    void stop();

    /**
     * Enables the AI to make its move.
     */
    void aiCanPlay();

    /**
     * Enables the player to make their move.
     */
    void playerCanPlay();
}
