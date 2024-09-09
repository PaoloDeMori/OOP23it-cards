package it.unibo.itcards.view;

import java.awt.Color;
import java.awt.Font;

import it.unibo.itcards.commons.Observer;

public interface View extends Observer {
    public static final Color INVISIBLE_COLOR = new Color(0, 0, 0, 0);
    public static final Color OPPONENT_COLOR = new Color(255, 0, 0);
    public static final Color PLAYER_COLOR = new Color(255, 217, 46);
    public static final int STANDARD_HGAP = 10;
    public static final int STANDARD_VGAP = 5;
    public static final Font STANDARD_FONT = new Font("Arial", Font.BOLD, 40);

    void show();

    void start();

    void stop();

    void aiCanPlay();

    void playerCanPlay();
}
