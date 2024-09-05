package it.unibo.itcards.model.scopa;

import it.unibo.itcards.commons.Card;
import it.unibo.itcards.model.Model;
import it.unibo.itcards.model.ScopaImpl;
import it.unibo.itcards.model.baseelements.player.AIPlayer;
import java.util.List;

public class AIScopaMedium extends AIPlayer{

    private ScopaImpl game;
    private List<Card> hand;
    private List<Card> table; 


    public AIScopaMedium(String name, int maxNumberOfCards) {
        super(name, maxNumberOfCards);
    }

    @Override
    public void setGame(Model game) {
        this.game = (ScopaImpl) game;
    }


    public boolean isOver10(Card card){
        Integer sumTable = table.stream().mapToInt(oneCard -> oneCard.getValue()).sum();
        return sumTable + card.getValue()>10 ? true : false;
    }

    @Override
    public Card chooseCard() {
        this.hand = getCards();
        this.table = this.game.getCardsOnTable();
        Card bestCard = null;

        for (Card card : hand){
            if (isOver10(card)){
                bestCard = card;
            }
        }


        return bestCard;

    }
    
}
