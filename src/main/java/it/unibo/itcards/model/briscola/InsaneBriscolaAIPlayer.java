package it.unibo.itcards.model.briscola;

import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import it.unibo.itcards.commons.Card;
import it.unibo.itcards.model.baseelements.player.InvalidOperationException;

public class InsaneBriscolaAIPlayer extends DifficultBriscolaAIPlayer {

    private Set<Card> playedCards = new HashSet<>();

    public InsaneBriscolaAIPlayer(String name, int maxNumberOfCards) {
        super(name, maxNumberOfCards);

    }

    /**
     * upgrade played card list
     * 
     * @param card the card to add
     */
    public void updatePlayedCards(Card card) {
        playedCards.add(card);
    }

    /**
     * count briscole left in the deck
     * 
     * @return the number of briscole left
     */
    public int countRemainingBriscole() {
        int totalBriscole = 10;
        long playedBriscole = playedCards.stream()
                .filter(card -> card.getSuit().equals(game.getBriscola().getSuit())).count();
        return totalBriscole - (int) playedBriscole;
    }

    /**
     * calculate the power card based on the context
     * 
     * @param card the card that calculate the power
     * @return the power point of card
     */
    public int calculateCardStrength(Card card) {
        int baseStrenght = card.getValue();

        if (card.getSuit().equals(game.getBriscola().getSuit())) {
            baseStrenght += 10;
        }

        long sameSuitePlayed = playedCards.stream()
                .filter(c -> c.getSuit().equals(card.getSuit())).count();
        baseStrenght -= sameSuitePlayed * 2;

        return baseStrenght;
    }

    private Card selectStrongestCard(List<Card> hand) {
        return hand.stream().max(this::compareCards).orElse(null);
    }

    private Card selectWeakestCard(List<Card> hand) {
        return hand.stream().min(this::compareCards).orElse(null);
    }

    private Card selectWeakestNotBriscolaCard(List<Card> hand) {
        return hand.stream().filter(card -> !card.getSuit().equals(game.getBriscola().getSuit()))
                .min(this::compareCards).orElse(null);
    }

    private Card selectAgainstBriscola(List<Card> hand) {
        Card cardToPlay = selectWeakestNotBriscolaCard(hand);
        if (cardToPlay == null) {
            cardToPlay = selectWeakestCard(hand);
        }

        return cardToPlay != null ? cardToPlay : hand.get(0);
    }

    private Card selectAgainstOpponentCard(List<Card> hand, Card opponenCard) {
        Card cardToPlay = hand.stream().filter(card -> card.getSuit()
                .equals(opponenCard.getSuit()) && card.getValue() > opponenCard.getValue()).findFirst().orElse(null);

        if (cardToPlay == null) {
            if (opponenCard.getValue() <= 7) {
                cardToPlay = hand.stream().filter(card -> card.getValue() < -7
                        && !card.getSuit().equals(game.getBriscola().getSuit())).findFirst()
                        .orElse(selectStrongestCard(hand));
            } else {
                cardToPlay = hand.stream().filter(card -> card.getSuit()
                        .equals(game.getBriscola().getSuit())).findFirst().orElse(selectStrongestCard(hand));
            }
        }
        return cardToPlay;
    }

    /**
     * Choose the better card
     * 
     * @return the card that play in the turn
     * @throws InvalidOperationException
     */

    @Override
    public Card chooseCard() throws InvalidOperationException {
        List<Card> hand = getCards();
        Card cardToPlay = null;

        if (!game.playedCards().isEmpty()) {
            Card opponentCard = game.playedCards().get(0);

            if (opponentCard.getSuit().equals(game.getBriscola().getSuit())) {
                cardToPlay = selectAgainstBriscola(hand);
            } else {
                cardToPlay = selectAgainstOpponentCard(hand, opponentCard);
            }
        } else {
            cardToPlay = selectStrongestCard(hand);
        }

        if (cardToPlay == null) {
            cardToPlay = hand.get(0);
        }
        playCard(cardToPlay);
        updatePlayedCards(cardToPlay);

        return cardToPlay;
    }

    public int compareCards(Card card1, Card card2) {
        int strenght1 = calculateCardStrength(card1);
        int strenght2 = calculateCardStrength(card2);

        return Integer.compare(strenght1, strenght2);
    }

    public List<Card> getPlayedCards() {
        return new ArrayList<>(this.playedCards);
    }

}
