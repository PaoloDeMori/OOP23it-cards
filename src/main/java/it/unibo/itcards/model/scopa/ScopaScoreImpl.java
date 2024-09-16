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

    PrimieraPoints primieraPoints = new PrimieraPoints();
    private Map<Player, List<Card>> allPlayedCardMap = new HashMap<>();

   
    public ScopaScoreImpl(Map<Player, List<Card>> allPlayedCardMap){
        this.allPlayedCardMap = allPlayedCardMap;
    }

    private Integer getPrimieraScore(Player player){
        int tot = 0;
        Optional<Integer> accumulator;
        for (Suit suit : Suit.values()) {
            accumulator = allPlayedCardMap.get(player).stream().filter(card -> card.getSuit() == suit)
            .map(card-> this.primieraPoints.getPrimieraScoreMap().get(card.getValue()))
            .sorted((c1, c2) -> c2-c1)
            .findFirst();

            if(accumulator.isPresent()) {
                tot += accumulator.get();
            }
            else {
                return 0;
            }

        }
        return tot;
    }

    
    private Integer countCoins(Player player){
        return allPlayedCardMap.get(player).stream().filter(card -> card.getSuit()==Suit.DENARI).toList().size();
    }

    private boolean hasSevenOfCoins(Player player){
        return allPlayedCardMap.get(player).stream().anyMatch(card -> card.getSuit()==Suit.DENARI && card.getValue()==7);
    }

    @Override
    public Optional<Player> winnerCards(){
        return allPlayedCardMap.entrySet().stream().max(Comparator.comparingInt(entry -> entry.getValue().size())).map(entry -> entry.getKey());
    }

    @Override
    public Optional<Player> winnerSevenOfCoins(){
        return allPlayedCardMap.keySet().stream().filter(player -> hasSevenOfCoins(player)).findFirst();
    }

    @Override
    public Optional<Player> winnerCoins (){
        return allPlayedCardMap.keySet().stream().max(Comparator.comparingInt(player -> countCoins(player)));
    }

    @Override
    public Optional<Player> winnerPrimiera(){
        return allPlayedCardMap.keySet().stream().max(Comparator.comparingInt(player -> getPrimieraScore(player)));
    }



}
