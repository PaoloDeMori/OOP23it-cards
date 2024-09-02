package it.unibo.itcards.model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import it.unibo.itcards.commons.Card;
import it.unibo.itcards.model.baseelements.player.AIPlayer;
import it.unibo.itcards.model.baseelements.player.Player;

public class ScopaImpl extends Model {
    private List<Card> cardsOnTable;
    private Map<Player, Set<Card>> scope; 

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
        //controllo che ci siano abbastanza carte TODO


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

    @Override
    public int points(Player player) {
        //for(var card : player.getWonCards())
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'points'");
    }

    public void winnerRound(Card playedCard){
        //controllo carte sul tavolo
        //Se c'è una sola carta aggiorno scope
        //se posso prendere aggiorno wonCards 
        //altrimenti aggiorno carte sul tavolo
        
        

    }

    public Player winner(){
        players.stream().forEach((player) -> player.setPoints(this.points(player)));
        return players.stream().max((p1, p2) -> p1.getPoints() - p2.getPoints()).get();
    }
    

    public Set<Card> getScope(Player player){
        return this.scope.get(player);
    }
    
}
