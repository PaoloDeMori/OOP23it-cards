package it.unibo.itcards.model.briscola;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import it.unibo.itcards.model.Model;
import it.unibo.itcards.model.baseelements.cards.Card;
import it.unibo.itcards.model.baseelements.player.AIPlayer;
import it.unibo.itcards.model.baseelements.player.PlayerImpl;

public class EasyBriscolaAIPlayer extends PlayerImpl implements AIPlayer {

    public EasyBriscolaAIPlayer(String name, int maxNumberOfCards) {
        super(name, maxNumberOfCards);
    }

    @Override
    public void setGame(Model game) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setGame'");
    }

    @Override
    public Card chooseCard() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'chooseCard'");
    }

   
}
