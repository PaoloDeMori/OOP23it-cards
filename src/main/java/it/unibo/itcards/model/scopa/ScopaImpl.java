package it.unibo.itcards.model.scopa;


import static java.util.stream.Collectors.toSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Comparator;


import it.unibo.itcards.commons.Card;
import it.unibo.itcards.model.InGameException;
import it.unibo.itcards.model.Model;
import it.unibo.itcards.model.baseelements.player.AIPlayer;
import it.unibo.itcards.model.baseelements.player.Player;

public class ScopaImpl extends Model {
    private List<Card> cardsOnTable;
    private Map<Player, Integer> scope;
    //private Map<Player, Set<Card>> scope; 

    public ScopaImpl(Player player , Player bot){
        super();
        this.cardsOnTable = new ArrayList<>();
        this.scope = new HashMap<>(); 
    }

    //ludo
    @Override
    public void playTurn(Card card, Player player) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'playTurn'");
    }

    @Override
    public List<Card> getCardsOnTable() {
        return this.cardsOnTable;
    }

    @Override
    public void start() {
        players.stream().filter(player -> player.isAi()).map(player-> (AIPlayer) player).forEach(player->player.setGame(this));
        for (int i=0; i<4; i++){
            cardsOnTable.add(this.deck.drawCard().get());
        }
        this.currentPlayer=players.get(0);
        
        
        this.giveCards();
        
    }

    //ludo
    @Override
    public boolean isGameOver() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isGameOver'");
    }


    @Override
    public boolean giveCards() {
        if (deck.isVoid()) {
            return false;
        }
        for(var Player : players){
            if(Player.getNumberOfCards()==0){
                Optional<Card> card;
                for(int i=0; i<3; i++){
                    card = deck.drawCard();
                    if (!card.isPresent()) {
                        throw new InGameException("Invalid card in the deck");
                    }
                    
                    Player.drawCard(card.get());
                }
                
            }
        }
        return true; 
    }


    private List<List<Card>> getSubsetsOnTable() {
        List<List<Card>> subsetCollection = new ArrayList<>();
        subsetCollection.add(new ArrayList<>());

        for (Card card : getCardsOnTable()) {
            for (int i = 0; i < subsetCollection.size(); i++) {
                List<Card> subset = new ArrayList<>(subsetCollection.get(i));
                subset.add(card);
                if (subset.stream().mapToInt(oneCard -> oneCard.getValue()).sum() <= Card.MAX_NUMERICAL_VALUE) {
                    subsetCollection.add(subset);
                }
            }
        }

        return subsetCollection.stream().sorted((l1, l2) -> l1.size() - l2.size()).toList();
    }


    private List<List<Card>> getShortestSubsets(int cardPlayedValue) {
        List<List<Card>> shortestSubsets = new ArrayList<>();
        for (List<Card> subset : getSubsetsOnTable()) {
            if (subset.stream().mapToInt(card -> card.getValue()).sum() == cardPlayedValue &&
                    (shortestSubsets.isEmpty() || shortestSubsets.get(0).size() == subset.size())) {

                shortestSubsets.add(subset);
            }
        }

        if (shortestSubsets.isEmpty()) {
            return new ArrayList<>();
        }
        return shortestSubsets;
    }

    public void playRound(Card playedCard){
        Player player = getCurrentPlayer();
        List<List<Card>> takingPossibility = getShortestSubsets(playedCard.getValue());
        
        if (takingPossibility.isEmpty()){
            this.cardsOnTable.add(playedCard);
        } else {

            //idk
            this.cardsOnTable.removeAll(takingPossibility.getFirst());
            player.addWonCards(takingPossibility.getFirst().stream().collect(toSet()));
            player.addPlayedCard(playedCard);
        }

        if (this.cardsOnTable.isEmpty()){
            this.scope.put(player, scope.getOrDefault(player, 0)+1);
        }

    }



    private Map<Player, List<Card>> populateAllPlayedCardsMap(){
        Map<Player, List<Card>> allPlayedCard = new HashMap<>();
        
        for (Player player : getPlayers()){
            allPlayedCard.put(player, player.getPlayedCards());
        }

        return allPlayedCard;
    }

    private void calculatePoints(Map<Player, Integer> playersScore, Optional<Player> player){
        int INCREMENT = 1;

        player.ifPresent(winPlayer -> 
        playersScore.put(winPlayer, playersScore.getOrDefault(winPlayer,0 )+INCREMENT));
    }


    public Player winner(){
        ScopaScoreImpl score = new ScopaScoreImpl(populateAllPlayedCardsMap());
        Map<Player, Integer> playersScore = new HashMap<>();
        
        calculatePoints(playersScore, score.winnerCards());
        calculatePoints(playersScore, score.winnerCoins());
        calculatePoints(playersScore, score.winnerSevenOfCoins());

        scope.entrySet().stream().forEach(entry ->
            playersScore.put(entry.getKey(), playersScore.getOrDefault(entry.getKey(), 0)+entry.getValue())
        );

        return playersScore.entrySet().stream().max(Comparator.comparingInt(entry -> entry.getValue())).map(entry -> entry.getKey()).get();
    }
    
    /* 
    public Set<Card> getScope(Player player){
        return this.scope.get(player);
    }
    */

    

    
    //inutile
    @Override
    public int points(Player player) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'points'");
    }
    
}
