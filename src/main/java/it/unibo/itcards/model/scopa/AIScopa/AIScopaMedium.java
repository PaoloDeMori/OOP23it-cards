package it.unibo.itcards.model.scopa.AIScopa;

import it.unibo.itcards.commons.Card;
import it.unibo.itcards.model.Model;
import it.unibo.itcards.model.baseelements.cards.Suit;
import it.unibo.itcards.model.baseelements.player.AIPlayer;
import it.unibo.itcards.model.scopa.PrimieraPoints;
import it.unibo.itcards.model.scopa.ScopaImpl;
import it.unibo.itcards.model.baseelements.cards.CardImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;


public class AIScopaMedium extends AIPlayer{

    private ScopaImpl game;
    private List<Card> hand;
    private List<Card> table;
    private static Card SEVEN_OF_COINS = new CardImpl(Suit.DENARI, 7);



    public AIScopaMedium(String name, int maxNumberOfCards) {
        super(name, maxNumberOfCards);
    }

    @Override
    public void setGame(Model game) {
        this.game = (ScopaImpl) game;
    }


    private boolean isOver10(Integer cardValue, Integer tableSum){
        return tableSum + cardValue > Card.MAX_NUMERICAL_VALUE ? true : false;
    }

    private List<Integer> populateList(){
        List<Integer> mostUsedCardsValue = new ArrayList<>();
        Map<Integer, Long> map = this.game.getAllPlayerCards().stream().collect(Collectors.groupingBy(Card::getValue, Collectors.counting()));
        
        mostUsedCardsValue = map.entrySet().stream().sorted(Map.Entry.comparingByValue()).map(entry -> entry.getKey()).toList();

        return mostUsedCardsValue;
    }   

    private boolean isPresentSevenOfCoins(List<Card> cards){
        return cards.stream().anyMatch(tableCard -> tableCard.equals(SEVEN_OF_COINS));
    }

    
    private List<Card> remainingCardsOnTable(List<Card> subset){
        List<Card> tableRemaining = new ArrayList<>(this.game.getCardsOnTable());
        tableRemaining.removeAll(subset);
        return tableRemaining; 
    }

    private Optional<Card> findBestCard(List<Card> hand, ToIntFunction<List<Card>> sumScoreFunction){
        int maxScore = Integer.MIN_VALUE;
        Optional<Card> bestCard = Optional.empty();

        for (Card card : hand) {
            for (List<Card> subset : this.game.getShortestSubsets(card.getValue())) {
                int currentScore = sumScoreFunction.applyAsInt(subset);
                
                if (currentScore > maxScore) {
                    maxScore = currentScore;
                    bestCard = Optional.of(card);
                }
            }
        }
        return bestCard;
    }
    
    @Override
    public Card chooseCard() {
        this.hand = getCards();
        this.table = this.game.getCardsOnTable();
        Integer sumTable = table.stream().mapToInt(oneCard -> oneCard.getValue()).sum();
        Optional<Card> cardToPlay = Optional.empty();
        PrimieraPoints primieraPoints = new PrimieraPoints();


        if(isPresentSevenOfCoins(hand) && !this.game.getShortestSubsets(SEVEN_OF_COINS.getValue()).isEmpty()) {
            return SEVEN_OF_COINS;
        }

        if(isPresentSevenOfCoins(table)) {
            cardToPlay = hand.stream().filter(card -> this.game.getShortestSubsets(card.getValue()).stream().anyMatch(subset -> subset.contains(SEVEN_OF_COINS))).findFirst();
        }

        if (cardToPlay.isPresent()) {
            return cardToPlay.get();
        }

        cardToPlay = findBestCard(hand,subset -> (int) subset.stream().filter(c1 -> c1.getSuit() == Suit.DENARI).count());

        if (cardToPlay.isPresent()) {
            return cardToPlay.get();
        }

        Map<Integer, Integer> scorePrimiera = primieraPoints.getPrimieraScoreMap();
        cardToPlay = findBestCard(hand, subset -> subset.stream().filter(subsetCard -> scorePrimiera.containsKey(subsetCard.getValue())).mapToInt(card -> scorePrimiera.get(card.getValue())).sum());

        if (cardToPlay.isPresent()) {
            return cardToPlay.get();
        }
        

        if(sumTable <= 10){
            cardToPlay = hand.stream().filter(card -> isOver10(card.getValue(), sumTable) && this.game.getShortestSubsets(card.getValue()).isEmpty()).findFirst();

            if (cardToPlay.isPresent()){
                return cardToPlay.get();
            } else {


                cardToPlay = findBestCard(hand, subset -> {
                    List<Integer> remaingValue = remainingCardsOnTable(subset).stream().mapToInt(Card::getValue).boxed().toList();
                    List<Integer> drawnValue = populateList();
                    return remaingValue.stream().filter(value -> drawnValue.contains(value)).mapToInt(val -> drawnValue.indexOf(val)).sum();
                    }
                );
            
            }
            if (cardToPlay.isPresent()) {
                return cardToPlay.get();
            }
        }


        Random rand = new Random();
        return hand.get(rand.nextInt(hand.size()));

    }
    
}
