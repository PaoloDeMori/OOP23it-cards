package it.unibo.itcards.view.baseelements.panels;
import javax.swing.JOptionPane;
import it.unibo.itcards.controller.Controller;

public class EndPane extends JOptionPane {

    Controller controller;
    String result;

    public EndPane(Controller controller,String result) {
        super();
        this.controller = controller;
        if(result.equals("pareggio")){
            this.result=result;
        }
        else{
            this.result=new String("Il vincitore è : ").concat(result);
        }
    }

    public void showTwoCommandsDialog() {
        String[] options = {"Nuova partita", "Esci"};
        int result = JOptionPane.showOptionDialog(
            null,
            this.result,
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