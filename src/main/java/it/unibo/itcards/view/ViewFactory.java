package it.unibo.itcards.view;

import it.unibo.itcards.view.baseelements.Dim;
import it.unibo.itcards.view.baseelements.mainpanel.MainPanel;

/**
 * The interface View factory.
 */
public interface ViewFactory {
    /**
     * Builds a view using the provided main panel and dimension.
     *
     * @param mainpanel the main panel to use in the view
     * @param dimension the dimension of the view
     * @return the built view
     */
    View build(MainPanel mainpanel, Dim dimension);

}
