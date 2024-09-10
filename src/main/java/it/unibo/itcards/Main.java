package it.unibo.itcards;

import it.unibo.itcards.controller.Controller;
import it.unibo.itcards.controller.ControllerImpl;
import it.unibo.itcards.model.baseelements.player.PlayerImpl;
import it.unibo.itcards.model.briscola.BriscolaImpl;
import it.unibo.itcards.model.briscola.DifficultBriscolaAIPlayer;
import it.unibo.itcards.view.baseelements.Dim;
import it.unibo.itcards.view.briscola.BriscolaView;

/**
 * The main class which starts the game and the view. It is the entry point of
 * the application.
 */
public final class Main {
    private Main() {
    }

    /**
     * The main entry point of the Java application.
     *
     * @param args an array of command line arguments
     */
    public static void main(final String[] args) {
        final Controller controller = new ControllerImpl();
        final BriscolaView briscolaView = new BriscolaView(Dim.MEDIUM, controller);
        final BriscolaImpl briscola = new BriscolaImpl(new PlayerImpl("gino", 3), new DifficultBriscolaAIPlayer("bot", 3));
        controller.init(briscola, briscolaView);
        briscola.start();
        briscolaView.start();
    }

}
