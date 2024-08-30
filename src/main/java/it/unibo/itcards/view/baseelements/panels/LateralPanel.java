package it.unibo.itcards.view.baseelements.panels;
import javax.swing.JPanel;
import java.awt.Dimension;

public abstract class LateralPanel extends JPanel{

    public abstract void setPoints();

    public abstract void setCenter(JPanel panel);

    public abstract void setNames();

    public abstract void init(Dimension d);
}
