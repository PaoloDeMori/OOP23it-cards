package it.unibo.itcards.model.baseelements;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import it.unibo.itcards.model.baseelements.cards.Card;
import it.unibo.itcards.model.baseelements.deck.Deck;
import it.unibo.itcards.model.baseelements.deck.ShuffledDeckFactoryImpl;
import it.unibo.itcards.model.baseelements.player.Player;

public abstract class Game implements Model {

    final List<Player> players = new ArrayList<Player>();
    final Deck deck = ShuffledDeckFactoryImpl.buildDeck();
    Player currentPlayer;

    @Override
    public abstract void playTurn(Card card, Player player);
    
    @Override
    public abstract List<Card> getCardsOnTable();

    @Override
    public abstract void start();

    @Override
    public boolean isGameOver() {
        for (Player player : players) {
            if (player.getNumberOfCards() != 0) {
                return false;
            }
        }
        if (deck.numberOfCards() != 0) {
            throw new InGameException("The players played all their cards but the game is not finished");
        }
        return true;
    }

    @Override
    public boolean isDeckEnded() {
        return deck.isVoid();
    }

    @Override
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    @Override
    public List<Player> getPlayers() {
        return this.players;
    }

    @Override
    public boolean giveCards() {
        if (deck.numberOfCards() >= players.size()) {
            for (Player player : players) {
                Optional<Card> card = deck.drawCard();
                if (card.isPresent()) {
                    player.drawCard(card.get());
                } else {
                    throw new InGameException("There was a fatal problem while the players were drawing their cards");
                }
            }
        }
        if (deck.numberOfCards() != 0 && deck.numberOfCards() < players.size()) {
            throw new InGameException("There was a fatal problem while the players were drawing their cards");
        }
        if (deck.numberOfCards() == 0) {
            return false;
        }
        return true;
    }

}
