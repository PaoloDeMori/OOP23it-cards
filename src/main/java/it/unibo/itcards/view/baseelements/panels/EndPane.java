package it.unibo.itcards.view.baseelements.panels;

import javax.swing.JOptionPane;
import it.unibo.itcards.controller.Controller;

/**
 * This class represents the end pane of the game.
 */
public class EndPane extends JOptionPane {

    private Controller controller;
    private String result;

    /**
     * Constructor. 
     * Creates a new EndPane. 
     * @param controller the controller
     * @param result     the result
     */
    public EndPane(final Controller controller, final String result) {
        super();
        this.controller = controller;
        if (result.equals("pareggio")) {
            this.result = result;
        } else {
            this.result = new String("Il vincitore è : ").concat(result);
        }
    }

    /**
     *    Displays a dialog with two commands: "Nuova partita" (New game) and "Esci" (Exit).
     *    The dialog shows the result of the game and waits for the user's choice.
     *    If the user chooses "Nuova partita", the newGame method is called.
     *    If the user chooses "Esci", the end method is called.
     */
    public void showTwoCommandsDialog() {
        String[] options = { "Nuova partita", "Esci" };
        int result = JOptionPane.showOptionDialog(
                null,
                this.result,
                "Fine",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.DEFAULT_OPTION,
                null,
                options,
                options[0]);

        if (result == 0) {
            newGame();
        } else if (result == 1) {
            end();
        }
    }

    /**
     * Starts a new game by calling the controller's start method.
     */
    public void newGame() {
        this.controller.start();
    }

    /**
     * Terminates the Java Virtual Machine (JVM) and exits the application.
     */
    public void end() {
        System.exit(0);
    }
}
