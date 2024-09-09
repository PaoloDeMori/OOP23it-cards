package it.unibo.itcards.model.briscola;

import java.util.List;
import java.util.ArrayList;

import it.unibo.itcards.commons.Card;
import it.unibo.itcards.model.Model;
import it.unibo.itcards.model.baseelements.player.AIPlayer;
import it.unibo.itcards.model.baseelements.player.InvalidOperationException;

import java.util.stream.*;;

/**
 * This class implements the player interface and represent a Difficult AI
 * player.
 * 
 */
public class DifficultBriscolaAIPlayer extends AIPlayer {
    private BriscolaImpl game;

    /**
     * Constructor
     * 
     * @param name
     * @param maxNumberOfCards
     */
    public DifficultBriscolaAIPlayer(final String name, final int maxNumberOfCards) {
        super(name, maxNumberOfCards);
    }

    /**
     * Sets the game for the AI player.
     *
     * @param game the game to be set
     */
    @Override
    public void setGame(final Model game) {
        this.game = (BriscolaImpl) game;
    }

    /**
     * Calculates the power of a given card in the game of Briscola.
     * The power is determined by the card's value and suit, as well as the cards
     * that have been played.
     *
     * @param card the card for which to calculate the power
     * @return the calculated power of the card
     */
    private int getCardPower(final Card card) {
        List<Card> totalPlayedCards = new ArrayList<>();
        for (var player : game.getPlayers()) {
            totalPlayedCards.addAll(player.getPlayedCards());
        }
        totalPlayedCards.addAll(this.getCards());
        int value = 0;
        for (Card playedCard : totalPlayedCards) {
            if (BriscolaHelper.isWinner(playedCard, card, this.game.getBriscola())) {
                value++;
            }
        }
        switch (card.getValue()) {
            case 1:
                return (card.getSuit() != this.game.getBriscola().getSuit() ? 10 - value : 0);
            case 2:
                return (card.getSuit() != this.game.getBriscola().getSuit() ? 19 - value : 9 - value);
            case 3:
                return (card.getSuit() != this.game.getBriscola().getSuit() ? 11 - value : 1 - value);
            default:
                return (card.getSuit() != this.game.getBriscola().getSuit() ? (22 - card.getValue()) - value
                        : (12 - card.getValue()) - value);
        }
    }

    /**
     * Chooses a card to play from the given hand without considering the Briscola.
     * The chosen card is the one with the lowest power, calculated based on its
     * value and suit.
     * If there are multiple cards with the same power, the one with the lowest
     * value is chosen.
     *
     * @param hand the list of cards to choose from
     * @return the chosen card to play
     */
    private Card chooseCardToPlayWithoutBriscola(final List<Card> hand) {
        Card cardToPlay = null;
        if (hand.stream().anyMatch((card) -> BriscolaHelper.getCardValue(card) == 0)) {
            cardToPlay = hand.stream().filter((card) -> BriscolaHelper.getCardValue(card) == 0)
                    .min((c1, c2) -> getCardPower(c1) - getCardPower(c2)).get();
            return cardToPlay;
        } else if (hand.stream().anyMatch((card) -> BriscolaHelper.getCardValue(card) <= 4)) {
            cardToPlay = hand.stream().filter((card) -> BriscolaHelper.getCardValue(card) <= 4)
                    .min((c1, c2) -> getCardPower(c1) - getCardPower(c2)).get();
            return cardToPlay;

        } else {
            cardToPlay = hand.stream().min((c1, c2) -> getCardPower(c1) - getCardPower(c2)).get();
            return cardToPlay;
        }
    }

    /**
     * Chooses a card to play from the given hand considering the Briscola.
     * The chosen card is the one with the highest power, calculated based on its
     * value and suit.
     * If there are multiple cards with the same power, the one with the highest
     * value is chosen.
     *
     * @param hand the list of cards to choose from
     * @return the chosen card to play
     */
    private Card chooseCardToPlayWithBriscola(final List<Card> hand) {
        Card cardToPlay = null;
        if (hand.stream().anyMatch((card) -> BriscolaHelper.getCardValue(card) == 0
                && card.getSuit() != this.game.getBriscola().getSuit())) {
            cardToPlay = hand.stream().filter((card) -> BriscolaHelper.getCardValue(card) == 0)
                    .min((c1, c2) -> getCardPower(c1) - getCardPower(c2)).get();
            return cardToPlay;
        } else if (hand.stream().anyMatch((card) -> BriscolaHelper.getCardValue(card) <= 4
                && card.getSuit() != this.game.getBriscola().getSuit())) {
            if (hand.stream().anyMatch((card) -> card.getSuit() == this.game.getBriscola().getSuit()
                    && BriscolaHelper.getCardValue(card) == 0)) {
                cardToPlay = hand.stream()
                        .filter((card) -> card.getSuit() == this.game.getBriscola().getSuit()
                                && BriscolaHelper.getCardValue(card) == 0)
                        .max((c1, c2) -> getCardPower(c1) - getCardPower(c2)).get();
                return cardToPlay;
            } else if (hand.stream().anyMatch((card) -> BriscolaHelper.getCardValue(card) <= 4
                    && card.getSuit() == this.game.getBriscola().getSuit())) {
                cardToPlay = hand.stream()
                        .filter((card) -> card.getSuit() != this.game.getBriscola().getSuit()
                                && BriscolaHelper.getCardValue(card) <= 4)
                        .max((c1, c2) -> getCardPower(c1) - getCardPower(c2)).get();
                return cardToPlay;
            }
        } else if (hand.stream().anyMatch((card) -> card.getSuit() != this.game.getBriscola().getSuit())) {
            cardToPlay = hand.stream().filter((card) -> card.getSuit() == this.game.getBriscola().getSuit())
                    .max((c1, c2) -> getCardPower(c1) - getCardPower(c2)).get();
            return cardToPlay;
        }

        cardToPlay = hand.stream().max((c1, c2) -> getCardPower(c1) - getCardPower(c2)).get();
        return cardToPlay;
    }

    /**
     * This function determines the best card to play against an opponent's card in
     * a game of Briscola when opponent's card isn't a briscola.
     * It considers various scenarios, including when the player has a card of the
     * same suit as the opponent's card,
     * when the player has a card of the Briscola suit, and when the opponent's card
     * has a value greater than 4.
     *
     * @param hand         the player's hand of cards
     * @param opponentCard the opponent's card
     * @return the best card to play against the opponent's card
     */
    private Card chooseCardToPlayAgainst(final List<Card> hand, final Card opponentCard) {
        Card cardToPlay = null;
        if (hand.stream().anyMatch((card) -> card.getSuit() == opponentCard.getSuit()
                && BriscolaHelper.isWinner(card, opponentCard, this.game.getBriscola()))) {
            cardToPlay = hand.stream()
                    .filter((card) -> card.getSuit() == opponentCard.getSuit()
                            && BriscolaHelper.isWinner(card, opponentCard, this.game.getBriscola()))
                    .max((c1, c2) -> BriscolaHelper.getCardValue(c1) - BriscolaHelper.getCardValue(c2)).get();
            return cardToPlay;
        } else if (hand.stream().anyMatch((card) -> card.getSuit() == this.game.getBriscola().getSuit())
                && BriscolaHelper.getCardValue(opponentCard) > 4) {
            cardToPlay = hand.stream().filter((card) -> card.getSuit() == this.game.getBriscola().getSuit())
                    .min((c1, c2) -> getCardPower(c1) - getCardPower(c2)).get();
            return cardToPlay;

        } else if (hand.stream().anyMatch((card) -> card.getSuit() == this.game.getBriscola().getSuit())
                && BriscolaHelper.getCardValue(opponentCard) == 0) {
            cardToPlay = hand.stream().max((c1, c2) -> getCardPower(c1) - getCardPower(c2)).get();
            return cardToPlay;
        } else {
            if (hand.stream().anyMatch((card) -> card.getSuit() == this.game.getBriscola().getSuit())
                    && BriscolaHelper.getCardValue(opponentCard) < 10) {
                cardToPlay = hand.stream()
                        .filter((card) -> card.getSuit() == this.game.getBriscola().getSuit()
                                && BriscolaHelper.getCardValue(opponentCard) < 10)
                        .max((c1, c2) -> getCardPower(c1) - getCardPower(c2)).get();
                return cardToPlay;
            } else {
                cardToPlay = hand.stream().max((c1, c2) -> getCardPower(c1) - getCardPower(c2)).get();
                return cardToPlay;
            }
        }
    }

    /**
     * This function determines the best card to play against an opponent's card in
     * a game of Briscola if the opponent's card is a briscola.
     * It considers various scenarios based on the opponent's card value and the
     * cards in the player's hand.
     *
     * @param hand         the player's hand of cards
     * @param opponentCard the card played by the opponent
     * @return the best card to play against the opponent's card
     */
    private Card chooseCardToPlayAgainstWithBriscola(final List<Card> hand, final Card opponentCard) {
        Card cardToPlay = null;
        if (BriscolaHelper.getCardValue(opponentCard) == 10) {
            if (hand.stream().anyMatch((card) -> card.getSuit() == this.game.getBriscola().getSuit()
                    && BriscolaHelper.getCardValue(card) == 11)) {
                cardToPlay = hand.stream().filter((card) -> card.getSuit() == this.game.getBriscola().getSuit()
                        && BriscolaHelper.getCardValue(card) == 11).collect(Collectors.toList()).get(0);
                return cardToPlay;
            } else {
                cardToPlay = hand.stream().max((c1, c2) -> getCardPower(c1) - getCardPower(c2)).get();
                return cardToPlay;
            }
        } else if (hand.stream().anyMatch((card) -> card.getSuit() != this.game.getBriscola().getSuit()
                && BriscolaHelper.getCardValue(card) < 4)) {
            cardToPlay = hand.stream()
                    .filter((card) -> card.getSuit() != this.game.getBriscola().getSuit()
                            && BriscolaHelper.getCardValue(card) < 4)
                    .max((c1, c2) -> getCardPower(c1) - getCardPower(c2)).get();
            return cardToPlay;
        } else if (hand.stream().anyMatch((card) -> card.getSuit() != this.game.getBriscola().getSuit()
                && BriscolaHelper.getCardValue(card) >= 4)) {
            if (hand.stream().anyMatch((card) -> card.getSuit() == this.game.getBriscola().getSuit())) {
                cardToPlay = hand.stream().filter((card) -> card.getSuit() == this.game.getBriscola().getSuit())
                        .max((c1, c2) -> getCardPower(c1) - getCardPower(c2)).get();
                return cardToPlay;
            } else {
                cardToPlay = hand.stream().max((c1, c2) -> getCardPower(c1) - getCardPower(c2)).get();
                return cardToPlay;
            }
        }

        return cardToPlay;
    }

    /**
     * This function determines the best card to play in a game of Briscola,
     * considering various scenarios based on the cards in the player's hand
     * and the cards that have been played.
     *
     * @return the best card to play
     * @throws InvalidOperationException 
     */
    @Override
    public Card chooseCard() throws InvalidOperationException {
        List<Card> hand = getCards();
        Card cardToPlay;
        if (this.game.playedCards().size() == 0) {
            if (hand.stream().anyMatch((card) -> card.getSuit() == this.game.getBriscola().getSuit())) {
                cardToPlay = this.chooseCardToPlayWithBriscola(hand);
            } else {
                cardToPlay = this.chooseCardToPlayWithoutBriscola(hand);
            }
        } else {
            if (this.game.playedCards().get(0).getSuit() != this.game.getBriscola().getSuit()) {
                cardToPlay = this.chooseCardToPlayAgainst(hand, this.game.playedCards().get(0));
            } else {
                cardToPlay = this.chooseCardToPlayAgainstWithBriscola(hand, this.game.playedCards().get(0));
            }
        }
        playCard(cardToPlay);
        return cardToPlay;
    }

}
