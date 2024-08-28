package it.unibo.itcards.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import it.unibo.itcards.model.baseelements.cards.Card;
import it.unibo.itcards.model.baseelements.deck.Deck;
import it.unibo.itcards.model.baseelements.player.Player;
import it.unibo.itcards.model.briscola.BriscolaHelper;

public class BriscolaImpl extends Model {

    private static final int NUMBER_OF_PLAYERS = 2;
    private Card briscola;
    private List<Card> playedCards;

    public BriscolaImpl() {
        super();
        this.playedCards = new ArrayList<>();
    }

    @Override
    public List<Card> getCardsOnTable() {
        final List<Card> cardsOnTable = new ArrayList<>();
        cardsOnTable.add(0, briscola);
        cardsOnTable.addAll(playedCards);
        return cardsOnTable;
    }

    @Override
    public boolean isGameOver() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void playTurn(Card card, Player player) {
        // TODO Auto-generated method stub

    }

    @Override
    public int points(Player player) {
        Set<Card> cards = player.getWonCards();
        int points = 0;
        for (Card card : cards) {
            points += BriscolaHelper.getCardValue(card);
        }
        return points;
    }

    @Override
    public void start() {
        Optional<Card> card = this.deck.drawCard();
        if (card.isPresent()) {
            setBriscola(card.get());
        } else {
            throw new InGameException("Deck is empty");
        }

    }

    @Override
    public Player winner() {
        if (BriscolaHelper.isWinner(playedCards.get(0), playedCards.get(1), briscola)) {
            for (var player : players) {
                if (player.getPlayedCards().contains(playedCards.get(0))) {
                    return player;

                }
            }
        }
        for (var player : players) {
            if (player.getPlayedCards().contains(playedCards.get(1))) {
                return player;
            }
        }
        throw new InGameException("No winner");
    }

    @Override
    public boolean giveCards() {

        Optional<Card> card;

        if (deck.isVoid()) {
            return false;
        }
        if (this.deck.numberOfCards() == 1) {
            card = deck.drawCard();
            if (!card.isPresent()) {
                throw new InGameException("Invalid card in the deck");
            }
            players.get(0).drawCard(card.get());
            players.get(1).drawCard(briscola);
            return true;
        }
        if (this.deck.numberOfCards() + 1 % NUMBER_OF_PLAYERS != 0) {
            throw new InGameException("Not enough cards to give");
        }
        for (var player : players) {
            card = deck.drawCard();
            if (!card.isPresent()) {
                throw new InGameException("Invalid card in the deck");
            }
            player.drawCard(card.get());
        }
        return true;
    }

    private void setBriscola(Card briscolCard) {
        this.briscola = briscolCard;
    }

}
