package it.unibo.itcards.model.scopa.AIScopa;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import it.unibo.itcards.commons.Card;
import it.unibo.itcards.model.Model;
import it.unibo.itcards.model.baseelements.player.AIPlayer;
import it.unibo.itcards.model.scopa.ScopaImpl;

public class AIScopaEasy extends AIPlayer {

    private ScopaImpl game;
    private List<Card> hand;

    public AIScopaEasy(String name, int maxNumberOfCards) {
        super(name, maxNumberOfCards);
    }

    @Override
    public void setGame(Model game) {
        this.game = (ScopaImpl) game;
    }

    @Override
    public Card chooseCard() {
        this.hand = getCards();
        Optional<Card> bestCard = Optional.empty();
        Random rand = new Random(); 

        bestCard = hand.stream().filter(card -> this.game.getShortestSubsets(card.getValue()).isEmpty()).findFirst(); 

        if(bestCard.isPresent()){
            return bestCard.get();
        }
        return hand.get(rand.nextInt(hand.size()));
    }
    
}