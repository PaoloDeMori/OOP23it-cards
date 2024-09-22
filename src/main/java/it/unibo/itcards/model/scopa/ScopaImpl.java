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
    private Map<Player, Integer> playersScore;

    public ScopaImpl(Player player , Player bot){
        super();
        this.cardsOnTable = new ArrayList<>();
        this.scope = new HashMap<>(); 
    }


    @Override
    public void playTurn(Card card, Player player) {
        if (player != this.getCurrentPlayer()) {
            throw new IllegalStateException("It's not your turn");
        }

        List<List<Card>> takingPossibility = getShortestSubsets(card.getValue());
        
        if (takingPossibility.isEmpty()){
            this.cardsOnTable.add(card);
        } else {
            this.cardsOnTable.removeAll(takingPossibility.getFirst());
            player.addWonCards(takingPossibility.getFirst().stream().collect(toSet()));
            player.addPlayedCard(card);
        }

        if (this.cardsOnTable.isEmpty()){
            this.scope.put(player, scope.getOrDefault(player, 0)+1);
        }
        this.notifyObserver();
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

    @Override
    public boolean isGameOver() {
        if (this.getDeck().numberOfCards() > 0) {
            return false;
        }

        for (final var player : this.getPlayers()) {
            if (player.getCards().size() > 0) {
                return false;
            }
        }

        return true;
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


    public List<List<Card>> getSubsetsOnTable(List<Card> table) {
        List<List<Card>> subsetCollection = new ArrayList<>();
        subsetCollection.add(new ArrayList<>());

        for (Card card : table) {
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


    public List<List<Card>> getShortestSubsets(int cardPlayedValue) {
        List<List<Card>> shortestSubsets = new ArrayList<>();
        for (List<Card> subset : getSubsetsOnTable(getCardsOnTable())) {
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
        playersScore = new HashMap<>();
        
        calculatePoints(playersScore, score.winnerCards());
        calculatePoints(playersScore, score.winnerCoins());
        calculatePoints(playersScore, score.winnerSevenOfCoins());

        scope.entrySet().stream().forEach(entry ->
            playersScore.put(entry.getKey(), playersScore.getOrDefault(entry.getKey(), 0)+entry.getValue())
        );

        return playersScore.entrySet().stream().max(Comparator.comparingInt(entry -> entry.getValue())).map(entry -> entry.getKey()).get();
    }
    

    @Override
    public int points(Player player) {
        return scope.get(player);
    }

    
    @Override
    public List<Integer> getPlayersPoints() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPlayersPoints'");
    }

    @Override
    public List<String> getPlayersNames() {
        return new ArrayList<>(players.stream().map(player -> player.getName()).toList());
    }
    

    public List<Card> getAllPlayerCards(){
        List<Card> allPlayedCards = new ArrayList<>();
        players.stream().forEach(player -> allPlayedCards.addAll(player.getPlayedCards()));
        return allPlayedCards;
    }

}
