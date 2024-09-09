package it.unibo.itcards.view;

import it.unibo.itcards.view.baseelements.Dim;
import it.unibo.itcards.view.baseelements.mainpanel.MainPanel;

public interface ViewFactory {

    View build(MainPanel mainpanel, Dim dimension);

}
