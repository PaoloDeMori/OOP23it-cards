package it.unibo.itcards;

import it.unibo.itcards.controller.Controller;
import it.unibo.itcards.controller.ControllerImpl;
import it.unibo.itcards.model.baseelements.player.PlayerImpl;
import it.unibo.itcards.model.briscola.BriscolaImpl;
import it.unibo.itcards.model.briscola.DifficultBriscolaAIPlayer;
import it.unibo.itcards.view.baseelements.Dim;
import it.unibo.itcards.view.briscola.BriscolaView;

public final class Main {
    private Main(){}

    public static void main(final String[] args) {
        Controller controller = new ControllerImpl();
        BriscolaView briscolaView = new BriscolaView(Dim.MEDIUM, controller);
        BriscolaImpl briscola = new BriscolaImpl(new PlayerImpl("gino", 3), new DifficultBriscolaAIPlayer("bot", 3));
        controller.init(briscola, briscolaView);
        briscola.start();
        briscolaView.start();
    }

}
