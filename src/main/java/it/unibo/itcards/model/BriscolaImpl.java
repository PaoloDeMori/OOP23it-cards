package it.unibo.itcards.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import it.unibo.itcards.commons.Card;
import it.unibo.itcards.model.baseelements.player.AIPlayer;
import it.unibo.itcards.model.baseelements.player.Player;
import it.unibo.itcards.model.briscola.BriscolaHelper;

public class BriscolaImpl extends Model {

    private static final int NUMBER_OF_PLAYERS = 2;
    private Card briscola;
    private List<Card> playedCards;
    private PlayerIterator iterator;

    /**
     * Constructor which initializes the BriscolaImpl object
     * with an empty ArrayList of playedCards.
     */
    public BriscolaImpl(Player player, Player bot) {
        super();
        this.playedCards = new ArrayList<>();
        this.iterator = new PlayerIterator(players);
        setPlayer(player, bot);
    }

    private void setPlayer(Player player, Player bot) {
        players.add(player);
        players.add(bot);
    }

    /**
     * Returns the list of cards currently on the table.
     * This includes the Briscola card followed by the cards played by the players.
     * 
     * @return a list of cards on the table.
     */
    @Override
    public List<Card> getCardsOnTable() {
        final List<Card> cardsOnTable = new ArrayList<>();
        cardsOnTable.add(0, briscola);
        cardsOnTable.addAll(playedCards);
        return cardsOnTable;
    }

    /**
     * Determines if the game is over by checking if any player still has cards.
     * This method iterates over all player and check if any player has one or more
     * cards left
     * in their hand. If at least one player has card, the game is considered
     * ongoing and the
     * method returns {@code false}. If no players have card left, the game is over
     * and the method
     * returns {@code true}.
     * 
     * @return {@code true} if no player has cards left(game over), {@code false}
     *         otherwise(game ongoing).
     */
    @Override
    public boolean isGameOver() {
        boolean haveCards = false;
        for (var player : players) {
            if (player.getCards().size() == 0&&this.deck.numberOfCards()==0) {
                haveCards = true;
            }
        }
        return haveCards;
    }

    /**
     * Manages a player's turn.
     * 
     * This method performs the following actions:
     * (1) if no cards have been played in the current round, the given card is
     * added to
     * the played cards, and the turn is passed to the next player.
     * (2) if there is already at least one card played, the method adds the given
     * card,
     * determines the winner of round, and assisgn all played cards to the winner.
     * The
     * winner becomes the next player to play
     * (3) the turn iterator is updated to set the new winner, and the played cards
     * are cleared
     * for the next round.
     * (4) New cards are dealt to the players if necessary, and observers are
     * notified of the
     * change in game state.
     * 
     * @param card   the card played by the player during the turn
     * @param player the player who is playing the card
     */
    @Override
    public void playTurn(Card card, Player player) {
        if (player != currentPlayer) {
            throw new IllegalStateException("It's not your turn");
        }
        if (playedCards.size() < 1) {
            playedCards.add(card);
            currentPlayer.addPlayedCard(card);
            currentPlayer = iterator.next();

        } else {
            playedCards.add(card);
            player.addPlayedCard(card);
            Player wonPlayer = winner(this.playedCards);
            wonPlayer.addWonCards(new HashSet<Card>(playedCards));
            currentPlayer = wonPlayer;
            iterator.setWinnerPlayer(wonPlayer);
            playedCards.clear();
            this.giveCards();
        }
        /* this.notifyObserver(); */

    }

    /**
     * Calculates the total points for the specified player.
     * 
     * @param player the player whose points are to be calculated.
     * @return the total points of the player.
     */
    @Override
    public int points(Player player) {
        Set<Card> cards = player.getWonCards();
        int points = 0;
        for (Card card : cards) {
            points += BriscolaHelper.getCardValue(card);
        }
        return points;
    }

    /**
     * Starts the game by drawing the first card from the deck and setting it as the
     * Briscola.
     * Throws an exception if the deck is empty.
     */
    @SuppressWarnings("unused")
    @Override
    public void start() {
        Optional<Card> card = this.deck.drawCard();
        players.stream().filter(player -> player instanceof AIPlayer).map(player -> (AIPlayer) player)
                .forEach(player -> player.setGame(this));
        if (card.isPresent()) {
            setBriscola(card.get());
        } else {
            throw new InGameException("Deck is empty");
        }
        iterator.setWinnerPlayer(players.get(0));
        this.currentPlayer = players.get(0);
        for (int i = 0; i < 3; i++) {
            this.giveCards();
        }
    }

    /**
     * Determines the winner of the current hand based on the cards played and the
     * Briscola.
     * 
     * @return the player who won the hand.
     * @throws InGameException if no winner can be determined.
     */
    @Override
    public Player winner(List<Card> playedCards) {
        if (playedCards.size() < 2) {
            throw new InGameException("Not enough cards played");
        }
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

    /**
     * Deals cards to the players. If the deck is empty or there are not enough
     * cards, returns false.
     * 
     * @return true if cards were successfully dealt, false otherwise.
     * @throws InGameException if an invalid card is drawn or if there are not
     *                         enough cards to deal.
     */
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
        if ((this.deck.numberOfCards() + 1) % NUMBER_OF_PLAYERS != 0) {
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

    /**
     * Determines the player with the highest points.
     *
     * @return the player with the highest points
     */
    public Player winnePlayer() {
        Player winner = null;
        for (var player : players) {
            if (winner == null) {
                winner = player;
            } else {
                if (this.points(player) > this.points(winner)) {
                    winner = player;
                }
            }
        }
        return winner;
    }

    /**
     * Sets the Briscola card for the game.
     * 
     * @param briscolaCard the card to be set as the Briscola.
     */
    public void setBriscola(Card briscolCard) {
        this.briscola = briscolCard;
    }

    /**
     * Returns the Briscola card of the game.
     */
    public Card getBriscola() {
        return briscola;
    }
    
    /**
     * Returns a list of all the cards that have been played in the game.
     *
     * @return  a list of played cards
     */
    public List<Card> playedCards() {
        return playedCards;
    }
    
    /**
     * Returns the number of players in the game.
     *
     * @return the number of players
     */
    public static int getNumberOfPlayers() {
        return NUMBER_OF_PLAYERS;
    }

    /**
     * Sets the list of played cards for the game.
     *
     * @param playedCards the list of cards that have been played
     */
    public void setPlayedCards(List<Card> playedCards) {
        this.playedCards = playedCards;
    }
    
    /**
     * Returns the PlayerIterator object associated with the game.
     *
     * @return the PlayerIterator object
     */
    public PlayerIterator getIterator() {
        return iterator;
    }

    /**
     * Sets the PlayerIterator object associated with the game.
     *
     * @param iterator  the PlayerIterator object to be set
     */
    public void setIterator(PlayerIterator iterator) {
        this.iterator = iterator;
    }

}
