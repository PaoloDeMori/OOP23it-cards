package it.unibo.itcards.model.briscola;

import java.util.List;

import it.unibo.itcards.commons.Card;
import it.unibo.itcards.model.InGameException;
import it.unibo.itcards.model.Model;
import it.unibo.itcards.model.baseelements.player.AIPlayer;
import it.unibo.itcards.model.baseelements.player.InvalidOperationException;

/**
 * This class represents an easy AI player.
 */
public class EasyBriscolaAIPlayer extends AIPlayer {

    private BriscolaImpl game;

    /**
     * This method set the game environment in wich the aiplayer is playing.
     * The aiplayer implementations will use this to calculate the best card to play
     * 
     * @param name             is the name to the AIplayer
     * @param maxNumberOfCards is the max number of cards to play
     * 
     */
    public EasyBriscolaAIPlayer(final String name, final int maxNumberOfCards) {
        super(name, maxNumberOfCards);
    }

    /**
     * Sets the game environment for the AI player.
     *
     * @param game the game to be set
     */
    @Override
    public void setGame(final Model game) {
        this.game = (BriscolaImpl) game;
    }

    /**
     * This method calculate the best card to play and returns it in easy mode.
     * 
     * @return an instance of the class card that represents the best card to play.
     * @throws InvalidOperationException
     */
    @Override
    public Card chooseCard() {
        List<Card> hand = getCards();
        Card tempCard = null;
        if (this.game.playedCards().size() == 0) {

            for (var card : hand) {
                if (tempCard == null) {
                    tempCard = card;
                } else if (BriscolaHelper.getCardValue(tempCard) > BriscolaHelper.getCardValue(card)) {
                    tempCard = card;
                }
            }
            try {
                this.playCard(tempCard);
            } catch (InvalidOperationException e) {
                throw new InGameException(e.getMessage());
            }
            return tempCard;
        } else {
            Card cardOnTable = this.game.playedCards().get(0);
            for (var card : hand) {
                if ((BriscolaHelper.isWinner(card, cardOnTable, this.game.getBriscola()))) {
                    if (tempCard == null) {
                        tempCard = card;
                    } else {
                        if (BriscolaHelper.isWinner(tempCard, card, this.game.getBriscola())) {
                            tempCard = card;
                        }
                    }
                }
            }
            if (tempCard == null) {
                for (var card : hand) {
                    if (tempCard == null) {
                        tempCard = card;
                    } else if (BriscolaHelper.getCardValue(tempCard) > BriscolaHelper.getCardValue(card)) {
                        tempCard = card;
                    }
                }
            }
        }

        try {
            this.playCard(tempCard);
        } catch (InvalidOperationException e) {
            throw new InGameException(e.getMessage());
        }
        return tempCard;

    }
}
