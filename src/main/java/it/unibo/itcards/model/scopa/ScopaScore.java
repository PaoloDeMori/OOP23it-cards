package it.unibo.itcards.model.scopa;

import java.util.Optional;

import it.unibo.itcards.model.baseelements.player.Player;

public interface ScopaScore {    
    
    Optional<Player> winnerCards();

    Optional<Player> winnerSevenOfCoins();

    Optional<Player> winnerCoins();

    Optional<Player> winnerPrimiera();
}
