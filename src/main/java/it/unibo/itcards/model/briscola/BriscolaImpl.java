package it.unibo.itcards.model.briscola;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import it.unibo.itcards.commons.Card;
import it.unibo.itcards.model.InGameException;
import it.unibo.itcards.model.Model;
import it.unibo.itcards.model.PlayerIterator;
import it.unibo.itcards.model.baseelements.player.AIPlayer;
import it.unibo.itcards.model.baseelements.player.Player;

/**
 * This class implements the Briscola game.
 */
public class BriscolaImpl extends Model {

    private static final int NUMBER_OF_PLAYERS = 2;
    private Card briscola;
    private List<Card> playedCards;
    private PlayerIterator iterator;
    private static final int TIME_TO_WAIT = 2000;

    /**
     * Constructor which initializes the BriscolaImpl object
     * with an empty ArrayList of playedCards.
     * 
     * @param player the human player in this game
     * @param bot    the bot that will play against the human player
     */
    public BriscolaImpl(final Player player, final Player bot) {
        super();
        this.playedCards = new ArrayList<>();
        this.iterator = new PlayerIterator(this.getPlayers());
        setPlayer(player, bot);
    }

    private void setPlayer(final Player player, final Player bot) {
        this.getPlayers().add(player);
        this.getPlayers().add(bot);
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
        for (final var card : playedCards) {
            cardsOnTable.add(card);
        }
        return cardsOnTable;
    }

    /**
     * Return true if the game is over, false otherwise.
     * 
     * @return true if the game is over, false otherwise.
     */
    @Override
    public boolean isGameOver() {
        if (this.getDeck().numberOfCards() > 0) {
            return false;
        }

        for (final var player : this.getPlayers()) {
            if (player.getCards().size() > 0) {
                return false;
            }
        }

        return true;
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
    public void playTurn(final Card card, final Player player) {
        if (!player.equals(this.getCurrentPlayer())) {
            return;
        }
        if (playedCards.isEmpty()) {
            playedCards.add(card);
            this.getCurrentPlayer().addPlayedCard(card);
            this.setCurrentPlayer(iterator.next());
            this.notifyObserver();
            try {
                Thread.sleep(TIME_TO_WAIT);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } else {
            playedCards.add(card);
            player.addPlayedCard(card);
            final Player wonPlayer = winner(this.playedCards);
            wonPlayer.addWonCards(new HashSet<Card>(playedCards));
            this.setCurrentPlayer(wonPlayer);
            iterator.setWinnerPlayer(wonPlayer);
            wonPlayer.setPoints(this.points(wonPlayer));
            this.notifyObserver();
            try {
                Thread.sleep(TIME_TO_WAIT);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            playedCards.clear();
            this.giveCards();
        }
        this.notifyObserver();
    }

    /**
     * Calculates the total points for the specified player.
     * 
     * @param player the player whose points are to be calculated.
     * @return the total points of the player.
     */
    @Override
    public int points(final Player player) {
        final Set<Card> cards = player.getWonCards();
        int points = 0;
        for (final Card card : cards) {
            points += BriscolaHelper.getCardValue(card);
        }
        return points;
    }

    /**
     * Starts the game by drawing the first card from the deck and setting it as the
     * Briscola.
     * Throws an exception if the deck is empty.
     */

    @Override
    public void start() {
        final Optional<Card> card = this.getDeck().drawCard();
        this.getPlayers().stream().filter(player -> player instanceof AIPlayer).map(player -> (AIPlayer) player)
                .forEach(player -> player.setGame(this));
        if (card.isPresent()) {
            setBriscola(card.get());
        } else {
            throw new InGameException("Deck is empty");
        }
        iterator.setWinnerPlayer(this.getPlayers().get(0));
        this.setCurrentPlayer(this.getPlayers().get(0));
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
    public Player winner(final List<Card> playedCards) {
        if (playedCards.size() < 2) {
            throw new InGameException("Not enough cards played");
        }
        if (BriscolaHelper.isWinner(playedCards.get(0), playedCards.get(1), briscola)) {
            for (final var player : this.getPlayers()) {
                if (player.getPlayedCards().contains(playedCards.get(0))) {
                    return player;

                }
            }
        }
        for (final var player : this.getPlayers()) {
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

        if (this.getDeck().isVoid()) {
            return false;
        }

        if (this.getDeck().numberOfCards() == 1) {
            final Optional<Card> card = this.getDeck().drawCard();
            if (card.isEmpty()) {
                throw new InGameException("Invalid card in the deck");
            }
            this.getPlayers().get(0).drawCard(card.get());
            this.getPlayers().get(1).drawCard(briscola);
            return true;
        }

        if ((this.getDeck().numberOfCards() + 1) % NUMBER_OF_PLAYERS != 0) {
            throw new InGameException("Not enough cards to give");
        }

        for (final var player : this.getPlayers()) {
            final Optional<Card> card = this.getDeck().drawCard();
            if (card.isEmpty()) {
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
        for (final var player : this.getPlayers()) {
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
    public void setBriscola(final Card briscolaCard) {
        this.briscola = briscolaCard;
    }

    /**
     * Returns the Briscola card of the game.
     * 
     * @return the card used as Briscola
     */
    public Card getBriscola() {
        return briscola;
    }

    /**
     * Returns a list of all the cards that have been played in the game.
     *
     * @return a list of played cards
     */
    public List<Card> playedCards() {
        return playedCards;
    }

    /**
     * Clears the list of played cards in the game.
     * 
     * This method resets the played cards to an empty list, effectively clearing
     * all cards that have been played.
     */
    public void clearPlayedCards() {
        this.playedCards.clear();
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
    public void setPlayedCards(final List<Card> playedCards) {
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
     * @param iterator the PlayerIterator object to be set
     */
    public void setIterator(final PlayerIterator iterator) {
        this.iterator = iterator;
    }

    /**
     * Returns the points of players in the game.
     *
     * @return the points of players
     */
    @Override
    public List<Integer> getPlayersPoints() {
        final List<Player> players = new ArrayList<>();
        players.addAll(this.getPlayers());
        final List<Integer> points = new ArrayList<>();

        final Optional<Player> bot = players.stream().filter(Player::isAi).findFirst();
        final Optional<Player> player = players.stream().filter(p -> !p.isAi()).findFirst();

        if (bot.isPresent() && player.isPresent()) {
            final int botPoints = bot.get().getPoints();
            final int playerPoints = player.get().getPoints();
            points.add(playerPoints);
            points.add(botPoints);
        }
        return points;

    }

    /**
     * Returns the names of the players in the game.
     *
     * @return the names of the players
     */
    @Override
    public List<String> getPlayersNames() {
        final List<Player> players = new ArrayList<>();
        players.addAll(this.getPlayers());
        final List<String> names = new ArrayList<>();

        final Optional<Player> bot = players.stream().filter(Player::isAi).findFirst();
        final Optional<Player> player = players.stream().filter(p -> !p.isAi()).findFirst();

        if (bot.isPresent() && player.isPresent()) {
            final String botPoints = bot.get().getName();
            final String playerPoints = player.get().getName();
            names.add(playerPoints);
            names.add(botPoints);
        }
        return names;
    }

}
