package game;

import game.set.Set;
import game.tile.Color;
import game.tile.Tile;
import util.Log;

import java.util.ArrayList;
import java.util.List;

public class Game {

    public boolean gameIsAlive;

    private Set deck; //Deck of tiles to pick from

    public List<Set> sets;

    public static void main(String[] args) {
        Game game = new Game();

        Player maman = new Player("Maman", game);
        Player alex = new Player("Alex", game);
        Player dan = new Player("Dan", game);

        game.setPlayers(maman, alex, dan);

        game.start();
    }

    List<Player> players;

    public Game() {
        players = new ArrayList<>();
        deck = new Set();
        sets = new ArrayList<>();
        generateDefaultDeck();
        shuffleDeck();
    }

    /*
     * Take tiles out of the box...!
     *
     * Initial tiles are :
     *
     * 26 tiles (ranging from 1 to 13 twice) of each color (4 colors)
     * 2 jokers (red and black)
     *
     * Total of 106 tiles
     */
    private void generateDefaultDeck() {

        // Default tiles (1 to 13 twice for each colors)
        for (Color c : Color.values()) {
            for (int x = 0; x < 2; x++) {
                for (int i = 1; i < 14; i++) {
                    boolean isTwin = (x == 0);
                    deck.add(new Tile(c, i, isTwin));
                    // System.out.println("game.tile.Tile color " + c + " with ID " + i + " generated. Is twin : " + isTwin);
                }
            }
        }

        // Joker tiles (RED face and BLACK face)
        deck.add(new Tile(Color.RED, 0, false));
        deck.add(new Tile(Color.BLACK, 0, false));

        //Make sure we get the correct amount of tiles
        assert deck.size() == ((4 * (2 * 13)) + 2);
    }

    /*
     * Shuffle them!
     */
    private void shuffleDeck() {
        deck.shuffle();
    }

    private void setPlayers(Player... players) {
        for (Player p : players) {
            p.getStartingCards();
            this.players.add(p);
        }

        Log.game("Welcome to our " + players.length + " players.");
    }

    /**
     * Starts the game
     */
    private void start() {

        gameIsAlive = true;

        Log.game("Deciding who's starting the play...");

        int highestGotten = -1;
        Player startingPlayer = null;

        for (Player p : players) {
            Tile c = p.takeOneCard();

            if (c.ID == 0) { //Special case for jokers
                Log.game(p.playerName + " got a joker.");
            } else {
                p.say("I got a " + c.ID + ".");
            }

            if (c.ID > highestGotten) {
                highestGotten = c.ID;
                startingPlayer = p;
            }

        }

        Log.game("Okay, " + startingPlayer.playerName + ", you're first.");

        startingPlayer.say("Great!");

        while (gameIsAlive) {

            for (Player p : players) {
                p.takeTurn();
            }

            Log.game("Next turn.");

        }

        Log.game("game.Game ended.");

    }

    //Get a card from the deck of card
    public Tile getCard() {

        if (deck.size() == 0) {
            System.err.println("Error : No more cards in deck!");

            gameIsAlive = false;

            return null;
        }

        return deck.remove(0);
    }

}
