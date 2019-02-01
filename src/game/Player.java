package game;

import game.set.Set;
import game.tile.Color;
import game.tile.Tile;
import util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Player {

    public final String playerName;

    private final Set currentTiles;

    private final Game game;

    public Player(String playerName, Game game) {
        this.playerName = playerName;
        this.currentTiles = new Set();
        this.game = game;
    }

    /**
     * Takes 7 cards from the deck to begin
     */
    public void getStartingCards() {
        for (int i = 0; i < GameTweaks.AMOUNT_OF_STARTING_TILES; i++) {
            takeOneCard();
        }
    }

    public Tile takeOneCard() {
        Tile tile = game.getCard();
        currentTiles.add(tile);
        return tile;
    }

    // Time to play!
    public void takeTurn() {

        boolean played = false;

        say("I have " + currentTiles.size() + " cards.");

        if (game.sets.size() == 0) {

            say("Looking if I have a run...");

            for (Color c : Color.values()) {

                Set cardsOfThisColor = currentTiles.getAllCardsForColor(c).sortByID();

                System.out.println(cardsOfThisColor);

            }
/*

            for (Tile c : currentTiles) {

                Set cardsOfTheSameColor = getAllCardsForColor(currentTiles, c.color);

                cardsOfTheSameColor.sortByID();

                for (int index = 0; index < cardsOfTheSameColor.size() + 1; index++) {

                    for (int size = 1; size < cardsOfTheSameColor.size() + 1; size++) {

                        Set ordering = getInRange(cardsOfTheSameColor, index, index + size);

                        if (ordering.size() > 3) {

                            if (Set.testIsAllSameColorAndIncremental(ordering)) {
                                System.out.println("Oh yeah!" + ordering);
                            }
                        }

                    }

                }

                //System.out.println("Found " + cardsOfTheSameColor.size() + " cards of the same color...");

                //System.out.println(Arrays.asList(cardsOfTheSameColor));
            /*if(game.set.Set.testIsAllSameColorAndIncremental(

                    getInRange(cardsOfTheSameColor, index, index +size)))

            {
                System.out.println("Oh yeah!");
            }

            }*/

            takeOneCard();

        }











        /*if (!played) {
            takeOneCard();
        }*/

    }

    public static List<Set> getAllPossibleWithoutChangingOrdering(Set list) {

        List<Set> resultList = new ArrayList<>();

        for (int index = 0; index < list.size() + 1; index++) {
            for (int size = 1; size < list.size() + 1; size++) {

                Set inRange = getInRange(list, index, index + (size));

                if (inRange.size() > 0) {
                    resultList.add(getInRange(list, index, index + (size)));
                }
            }
        }

        return resultList;
    }

    public static void main(String[] args) {

        Set s = new Set();

        s.add(new Tile(Color.RED, 1));
        s.add(new Tile(Color.RED, 2));
        s.add(new Tile(Color.RED, 3));
        s.add(new Tile(Color.RED, 4));

        for (Set ss : getAllPossibleWithoutChangingOrdering(s)) {
            System.out.println(ss);
        }

    }

    /**
     * Gets values between the range beginIndex and endIndex
     * <p>
     * If endIndex is larger than the size of the array, it's wrapped
     */
    public static <Card> Set getInRange(Set list, int beginIndex, int endIndex) {

        Set rangedValues = new Set();

        for (int i = beginIndex; i < endIndex; i++) {

            int index = i;

            if (i >= list.size()) {
                index = (i) - (list.size());
            }

            rangedValues.add(list.get(index));
        }

        return rangedValues;

    }

    /**
     * Utility method to getAllPermutations.
     */
    private List<List<Tile>> getAllPermutations(List<Tile> items) {
        List<List<Tile>> result = new ArrayList<>();
        getAllPermutations(items, items.size(), new Stack<Tile>(), result);
        return result;
    }

    /**
     * Gets all the possible ordering (permutations) for the given cards
     */
    private void getAllPermutations(List<Tile> items, int size, Stack<Tile> permutation, List<List<Tile>> result) {
        Log.debug(Arrays.toString(permutation.toArray()));
        result.add(new ArrayList<>(permutation));

        /* items available for permutation */
        List<Tile> availableItems = new ArrayList<>(items);

        for (Tile i : availableItems) {
            /* add current item */
            permutation.push(i);

            /* remove item from available item set */
            items.remove(i);

            /* pass it on for next permutation */
            getAllPermutations(items, size, permutation, result);

            /* pop and put the removed item back */
            items.add(permutation.pop());
        }
    }

    public void say(String s) {
        Log.player(this.playerName, s);
    }
}
