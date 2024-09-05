package it.unibo.itcards.model.scopa;

import it.unibo.itcards.commons.Card;
import it.unibo.itcards.model.Model;
import it.unibo.itcards.model.ScopaImpl;
import it.unibo.itcards.model.baseelements.player.AIPlayer;

public class AIScopaEasy extends AIPlayer {

    private ScopaImpl game;

    public AIScopaEasy(String name, int maxNumberOfCards) {
        super(name, maxNumberOfCards);
    }

    @Override
    public void setGame(Model game) {
        this.game = (ScopaImpl) game;
    }

    @Override
    public Card chooseCard() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'chooseCard'");
    }
    
}