package it.unibo.itcards.model.scopa;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import it.unibo.itcards.commons.Card;
import it.unibo.itcards.model.baseelements.cards.Suit;
import it.unibo.itcards.model.baseelements.player.Player;


public class ScopaScoreImpl implements ScopaScore{
    
    private static final Map<Integer, Integer> scorePrimiera = new HashMap<>();
    private Map<Player, List<Card>> allPlayedCard = new HashMap<>();

    static {
        scorePrimiera.put(7, 21);
        scorePrimiera.put(6, 18);
        scorePrimiera.put(1,16);
        scorePrimiera.put(5,15);
        scorePrimiera.put(4, 14);
        scorePrimiera.put(3, 13);
        scorePrimiera.put(2, 12);
        scorePrimiera.put(9, 10);
        scorePrimiera.put(8,10);
    }

    public ScopaScoreImpl(Map<Player, List<Card>> allPlayedCard){
        this.allPlayedCard = allPlayedCard;
    }

    private Integer getPrimieraScore(Player player){
        Integer score = 0;
        List<Integer> deckValue = allPlayedCard.get(player).stream().mapToInt(Card::getValue).boxed().toList();
    
        for (Integer valueCard : deckValue){
            if (scorePrimiera.keySet().contains(valueCard)) {
                score += scorePrimiera.get(valueCard);
            }
        }

        return score;
    }

    
    private Integer countCoins(Player player){
        return allPlayedCard.get(player).stream().filter(card -> card.getSuit()==Suit.DENARI).toList().size();
    }

    private boolean hasSevenOfCoins(Player player){
        return allPlayedCard.get(player).stream().anyMatch(card -> card.getSuit()==Suit.DENARI && card.getValue()==7);
    }

    @Override
    public Optional<Player> winnerCards(){
        return allPlayedCard.entrySet().stream().max(Comparator.comparingInt(entry -> entry.getValue().size())).map(entry -> entry.getKey());
    }

    @Override
    public Optional<Player> winnerSevenOfCoins(){
        return allPlayedCard.keySet().stream().filter(player -> hasSevenOfCoins(player)).findFirst();
    }

    @Override
    public Optional<Player> winnerCoins (){
        return allPlayedCard.keySet().stream().max(Comparator.comparingInt(player -> countCoins(player)));
    }

    @Override
    public Optional<Player> winnerPrimiera(){
        return allPlayedCard.keySet().stream().max(Comparator.comparingInt(player -> getPrimieraScore(player)));
    }



}
