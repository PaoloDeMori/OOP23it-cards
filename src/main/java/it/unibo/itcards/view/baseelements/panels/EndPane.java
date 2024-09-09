package it.unibo.itcards.view.baseelements.panels;
import javax.swing.JOptionPane;
import it.unibo.itcards.controller.Controller;

public class EndPane extends JOptionPane {

    Controller controller;

    public EndPane(Controller controller) {
        super();
        this.controller = controller;
    }

    public void showTwoCommandsDialog() {
        String[] options = {"Nuova partita", "Esci"};
        int result = JOptionPane.showOptionDialog(
            null,
            "Vuoi fare un'altra partita?",
            "Fine",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.DEFAULT_OPTION,
            null,
            options,
            options[0]
        );
        
        if (result == 0) {
            newGame();
        } else if (result == 1) {
            end();
        }
    }

    public void newGame() {
        this.controller.start();
    }

    public void end() {
        System.exit(0);
    }
}