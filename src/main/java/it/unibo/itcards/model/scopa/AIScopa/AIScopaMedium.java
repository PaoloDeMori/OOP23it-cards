package it.unibo.itcards.model.scopa.AIScopa;

import it.unibo.itcards.commons.Card;
import it.unibo.itcards.model.Model;
import it.unibo.itcards.model.baseelements.cards.Suit;
import it.unibo.itcards.model.baseelements.player.AIPlayer;
import it.unibo.itcards.model.scopa.ScopaImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Collections;

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


    private boolean isOver10(Card card, Integer table){
        return table + card.getValue()>10 ? true : false;
    }

    private List<Integer> populateList(){
        List<Integer> mostUsedCardsValue = new ArrayList<>();
        Map<Integer, Long> map = getPlayedCards().stream().collect(Collectors.groupingBy(Card::getValue, Collectors.counting()));
        
        List<Long> occurencesList = new ArrayList<>();
        Collections.sort(occurencesList);

        for (Long occ : occurencesList){
            for(var entry : map.entrySet()){
                if (entry.getValue().equals(occ)){
                    mostUsedCardsValue.add(entry.getKey());
                }
            }
        }

        return mostUsedCardsValue;
    }   

    private boolean isPresentSevenOfCoins(List<Card> cards){
        return cards.stream().anyMatch(tableCard -> tableCard.getSuit()==Suit.DENARI && tableCard.getValue()==7);
    }


    @Override
    public Card chooseCard() {
        this.hand = getCards();
        this.table = this.game.getCardsOnTable();
        Integer sumTable = table.stream().mapToInt(oneCard -> oneCard.getValue()).sum();
        Optional<Card> bestCard = Optional.empty();

        if(isPresentSevenOfCoins(hand) && table.stream().anyMatch(handCard -> handCard.getValue()==7)){
            bestCard = hand.stream().filter(card -> card.getValue()==7 && card.getSuit()==Suit.DENARI).findFirst();
        }


        if(isPresentSevenOfCoins(table) && hand.stream().anyMatch(handCard -> handCard.getValue()==7)){
            bestCard = hand.stream().filter(card -> card.getValue()==7).findFirst();
        }



        
        

        if(sumTable >= 10){
            

        } else{
            bestCard = hand.stream().filter(card -> isOver10(card, sumTable)).findFirst();

            if (!bestCard.isPresent()){
                bestCard = hand.stream().filter( card -> populateList().contains(card.getValue())).findFirst();
            }
        }




        



        return bestCard.orElse(null);

    }
    
}
