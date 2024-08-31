package it.unibo.itcards.model.briscola;

import java.util.List;
import java.util.ArrayList;
import it.unibo.itcards.model.BriscolaImpl;
import it.unibo.itcards.model.Model;
import it.unibo.itcards.model.baseelements.cards.Card;
import it.unibo.itcards.model.baseelements.player.AIPlayer;
import it.unibo.itcards.model.baseelements.player.PlayerImpl;

public class DifficultBriscolaAIPlayer extends PlayerImpl implements AIPlayer {
    private BriscolaImpl game;

    public DifficultBriscolaAIPlayer(String name, int maxNumberOfCards) {
        super(name, maxNumberOfCards);
    }

    @Override
    public void setGame(Model game) {
        this.game = (BriscolaImpl) game;
    }

    private int getCardPower(final Card card) {

        switch (card.getValue()) {
            case 1:
                return (card.getSuit()==this.game.getBriscola().getSuit() ? 10 :0 );
            case 2:
                return (card.getSuit()==this.game.getBriscola().getSuit() ? 19 :9 );
            case 3:
                return (card.getSuit()==this.game.getBriscola().getSuit() ?  11:1 );
            default:
                return (card.getSuit()==this.game.getBriscola().getSuit() ? 22-card.getValue(): 12-card.getValue());
        }
    }

    private Card chooseCardToPlayWithoutBriscola(List<Card> hand) {
        Card cardToPlay = null;
        if(hand.stream().anyMatch((card)-> BriscolaHelper.getCardValue(card)==0)){
            cardToPlay = hand.stream().filter((card) -> BriscolaHelper.getCardValue(card) == 0).min((c1, c2) -> getCardPower(c1) - getCardPower(c2)).get();
            return cardToPlay;
        }
        else if(hand.stream().anyMatch((card)-> BriscolaHelper.getCardValue(card)<= 4)){
            cardToPlay = hand.stream().filter((card) -> BriscolaHelper.getCardValue(card)<= 4).min((c1, c2) -> getCardPower(c1) - getCardPower(c2)).get();
            return cardToPlay;
            
        }
        else{
            cardToPlay = hand.stream().min((c1, c2) -> getCardPower(c1) - getCardPower(c2)).get();
            return cardToPlay;
        }
    }
    
    private Card chooseCardToPlayWithBriscola(final List<Card> hand) {
        Card cardToPlay = null;
        if(hand.stream().anyMatch((card)-> BriscolaHelper.getCardValue(card)==0 && card.getSuit() != this.game.getBriscola().getSuit())){
            cardToPlay = hand.stream().filter((card) -> BriscolaHelper.getCardValue(card) == 0).min((c1, c2) -> getCardPower(c1) - getCardPower(c2)).get();
            return cardToPlay;
        }
        else if(hand.stream().anyMatch((card)-> BriscolaHelper.getCardValue(card)<= 4 && card.getSuit() != this.game.getBriscola().getSuit())){
            if(hand.stream().anyMatch((card) -> card.getSuit()== this.game.getBriscola().getSuit() && BriscolaHelper.getCardValue(card)==0)){
                cardToPlay = hand.stream().filter((card) -> card.getSuit()== this.game.getBriscola().getSuit() && BriscolaHelper.getCardValue(card)==0).min((c1, c2) -> getCardPower(c1) - getCardPower(c2)).get();
            return cardToPlay;
            }
            else if(hand.stream().anyMatch((card)-> BriscolaHelper.getCardValue(card)<= 4 && card.getSuit()== this.game.getBriscola().getSuit() )){
            cardToPlay = hand.stream().filter((card)->card.getSuit() != this.game.getBriscola().getSuit() && BriscolaHelper.getCardValue(card)<= 4).min((c1, c2) -> getCardPower(c1) - getCardPower(c2)).get();
            return cardToPlay;
            }
        }
        else if(hand.stream().anyMatch((card)-> card.getSuit()!= this.game.getBriscola().getSuit())){
            cardToPlay = hand.stream().filter((card) -> card.getSuit()== this.game.getBriscola().getSuit()).max((c1, c2) -> getCardPower(c1) - getCardPower(c2)).get();
            return cardToPlay;
        }
    
        cardToPlay = hand.stream().max((c1, c2) -> getCardPower(c1) - getCardPower(c2)).get();
        return cardToPlay;
    }

    private Card chooseCardToPlayAgainst(List<Card> hand , Card opponentCard) {
        Card cardToPlay = null;
        if(hand.stream().anyMatch((card)-> card.getSuit()== opponentCard.getSuit())){
            cardToPlay= hand.stream().filter((card)-> card.getSuit()== opponentCard.getSuit()).max((c1, c2) -> BriscolaHelper.getCardValue(c1) - BriscolaHelper.getCardValue(c2)).get();
            return cardToPlay;
        }
        cardToPlay = hand.stream().max((c1, c2) -> getCardPower(c1) - getCardPower(c2)).get();
        return cardToPlay;

        //gestire il caso in cui la carta dell'opponent sia un carico/punti (niente briscola) fare controllo se effettivamente prendiamo con lo stesso segno
    }
    private Card chooseCardToPlayAgainstWithBriscola(List<Card> hand, Card opponentCard) {
        Card cardToPlay = null; 
        if(hand.stream().anyMatch((card)-> card.getSuit()!= this.game.getBriscola().getSuit() && BriscolaHelper.getCardValue(card)==0)){
            cardToPlay = hand.stream().filter((card) -> card.getSuit()!= this.game.getBriscola().getSuit() && BriscolaHelper.getCardValue(card)==0).max((c1, c2) -> getCardPower(c1) - getCardPower(c2)).get();
            return cardToPlay;
        }
        

        return cardToPlay;
    }
    @Override
    public Card chooseCard() {
        List<Card> hand = getCards();
        Card tempCard = null;
        List<Card> totalPlayedCards = new ArrayList<>();
        for (var player : game.getPlayers()) {
            totalPlayedCards.addAll(player.getPlayedCards());
        }
        totalPlayedCards.addAll(hand);
        if (this.game.playedCards().size() == 0) {
            if(hand.stream().anyMatch((card) -> card.getSuit() == this.game.getBriscola().getSuit())){
                return this.chooseCardToPlayWithBriscola(hand);
            }
            else{
                return this.chooseCardToPlayWithoutBriscola(hand);
            }
        }
        else{
            if(this.game.playedCards().get(0).getSuit() != this.game.getBriscola().getSuit()){
              return this.chooseCardToPlayAgainst(hand , this.game.playedCards().get(0));  
            } 
            else{
                return this.chooseCardToPlayAgainstWithBriscola(hand , this.game.playedCards().get(0));
            }
        }
    }



}
