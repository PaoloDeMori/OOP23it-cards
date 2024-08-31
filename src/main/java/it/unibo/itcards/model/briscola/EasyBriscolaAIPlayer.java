package it.unibo.itcards.model.briscola;

import java.util.List;
import it.unibo.itcards.model.BriscolaImpl;
import it.unibo.itcards.model.InGameException;
import it.unibo.itcards.model.Model;
import it.unibo.itcards.model.baseelements.cards.Card;
import it.unibo.itcards.model.baseelements.player.AIPlayer;
import it.unibo.itcards.model.baseelements.player.InvalidOperationException;
import it.unibo.itcards.model.baseelements.player.PlayerImpl;

public class EasyBriscolaAIPlayer extends PlayerImpl implements AIPlayer {

    private BriscolaImpl game;

    public EasyBriscolaAIPlayer(String name, int maxNumberOfCards) {
        super(name, maxNumberOfCards);
    }

    @Override
    public void setGame(Model game) {
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
