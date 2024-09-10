package it.unibo.itcards.controller;

import it.unibo.itcards.commons.Card;
import it.unibo.itcards.model.InGameException;
import it.unibo.itcards.model.Model;
import it.unibo.itcards.model.baseelements.player.Player;
import it.unibo.itcards.view.View;

import java.util.List;

/**
 * The controller of this application.
 */
public class ControllerImpl implements Controller {
    private Model model;
    private View view;

    /**
     * Sets the model and the view of the mvc pattern of this application.
     * 
     * @param model the game that the user decided to play
     * @param view  the graphical interface
     */
    @Override
    public void init(final Model model, final View view) {
        this.setModel(model);
        this.setView(view);
        this.model.addObserver(this.view);
    }

    /**
     * This method is called to start the game.
     */
    @Override
    public void start() {
        model.start();
        if (this.model.getCurrentPlayer().isAi()) {
            this.play(null);
        }
    }

    /**
     * This method is called by action listeners, it accepts a card as a parameter
     * that represents the card to play.
     * This method select the card for the player to playe and while the current
     * player is an ai it keeps asking them to play.
     * it called the play method.
     * If the game ended this method will handle that situation by calling the end
     * method.
     * 
     * @param card the card to play, when this method is called by a user.
     */
    @Override
    public void playturn(final Card card) {
        new Thread(() -> {
            model.getCurrentPlayer().selectCard(card);
            do {
                if (!model.getCurrentPlayer().isAi()) {
                    this.view.aiCanPlay();
                    play(card);
                    if (model.isGameOver()) {
                        model.notifyObserver();
                        end();
                    }
                }
                if (model.getCurrentPlayer().isAi()) {
                    play(null);
                    if (model.isGameOver()) {
                        model.notifyObserver();
                        end();
                    }
                }

            } while (this.model.getCurrentPlayer().isAi());
            this.view.playerCanPlay();
            model.notifyObserver();
        }).start();
    }

    /**
     * Sets the game the users decided to play.
     * 
     * @param model the game choose by the user
     */
    private void setModel(final Model model) {
        this.model = model;
    }

    /**
     * Sets the gui.
     * 
     * @param view the gui implemantation to run this application.
     */
    private void setView(final View view) {
        this.view = view;
    }

    /**
     * Handles what happened when there is an error that stopped the correct
     * execution of this program.
     */
    private void error() {
        System.exit(0);
    }

    /**
     * Returns the hand of the human player of this application.
     * 
     * @return the hand of the human player of the game.
     */
    @Override
    public List<Card> getHand() {
        final List<Player> players = this.model.getPlayers();
        int pos = 0;
        Player p = players.get(pos);
        while (p.isAi()) {
            pos++;
            p = players.get(pos);
        }
        if (!(p.isAi())) {
            return p.getCards();
        }
        throw new InGameException("The player required does not exists");
    }

    /**
     * Returns the hand of the AI player of this application.
     * 
     * @return the hand of the AI player of the game.
     */
    @Override
    public int getOpponentHand() {
        final List<Player> players = this.model.getPlayers();
        int pos = 0;
        Player p = players.get(pos);
        while (!(p.isAi())) {
            pos++;
            p = players.get(pos);
        }
        return p.getCards().size();
    }

    /**
     * Return the cards on table of the game.
     * 
     * @return the cards on table of the model.
     */
    @Override
    public List<Card> getCardsOnTable() {
        return this.model.getCardsOnTable();
    }

    /**
     * Return the number of cards in the deck.
     * 
     * @return the number of cards in the deck.
     */
    @Override
    public int deckNumberOfCards() {
        return this.model.getDeck().numberOfCards();
    }

    /**
     * Handles what happens when the game ends.
     */
    private void end() {
        this.view.stop();
    }

    /**
     * Handle what happens when a Player decided to play
     * When a player decided to play, this method is called, this calls the playturn
     * method of the model
     * using the playCard method of the Player interface, to play in the model the
     * card the player decided to play.
     * 
     * @param card the card the player wants to play, or null if it is called by a
     *             bot
     */
    private void play(final Card card) {
        try {
            model.playTurn(this.model.getCurrentPlayer().chooseCard(), this.model.getCurrentPlayer());
        } catch (Exception e) {
            this.error();
            return;
        }
    }

    /**
     * Returns the list of players in the game.
     *
     * @return the list of players
     */
    @Override
    public List<Player> getPlayers() {
        return this.model.getPlayers();
    }

    /**
     * Start the audio of the game.
     */
    @Override
    public void startAudio() {
        this.model.startAudio();
    }

    /**
     * Stop the audio of the game.
     */
    @Override
    public void stopAudio() {
        this.model.stopAudio();
    }

    /**
     * Returns the current player of the game.
     *
     * @return the current player
     */
    @Override
    public Player getCurrentPlayer() {
        return this.model.getCurrentPlayer();
    }

    /**
     * Return the points of the players of the game.
     * 
     * @return the points of the players
     */
    @Override
    public List<Integer> getPlayerPoints() {
        return this.model.getPlayersPoints();
    }

    /**
     * Return the names of the players of the game.
     * 
     * @return the names of the players
     */
    @Override
    public List<String> getPlayerNames() {
        return this.model.getPlayersNames();
    }

}
