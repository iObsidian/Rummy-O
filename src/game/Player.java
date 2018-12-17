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

    private final List<Tile> currentTiles;

    private final Game game;

    public Player(String playerName, Game game) {
        this.playerName = playerName;
        this.currentTiles = new ArrayList<Tile>();
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

        System.out.println(playerName + ", I have " + currentTiles.size() + " cards.");


        for (Set group : game.groups) {


            System.out.println();


        }


        for (Tile c : currentTiles) {

            List<Tile> cardsOfTheSameColor = getAllCardsForColor(currentTiles, c.color);

            Tile.sortByID(cardsOfTheSameColor);

            for (int index = 0; index < cardsOfTheSameColor.size() + 1; index++) {

                for (int size = 1; size < cardsOfTheSameColor.size() + 1; size++) {

                    List<Tile> ordering = getInRange(cardsOfTheSameColor, index, index + size);

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
            }*/


        }

        takeOneCard();


    }

    public static <T> List<List<T>> getAllPossibleWithoutChangingOrdering(List<T> list) {

        List<List<T>> resultList = new ArrayList<>();

        for (int index = 0; index < list.size() + 1; index++) {
            for (int size = 1; size < list.size() + 1; size++) {

                List<T> inRange = getInRange(list, index, index + (size));

                if (inRange.size() > 0) {
                    resultList.add(getInRange(list, index, index + (size)));
                }
            }
        }

        return resultList;
    }


    public static void main(String[] args) {

        List<String> s = new ArrayList<>();


        s.add("Hello");
        s.add("my");
        s.add("name");
        s.add("is");
        s.add("slim");
        s.add("shady");
        s.add(".");

        for (List<String> ss : getAllPossibleWithoutChangingOrdering(s)) {

            System.out.println(ss);
        }


    }


    /**
     * Gets values between the range beginIndex and endIndex
     * <p>
     * If endIndex is larger than the size of the array, it's wrapped
     */
    public static <T> List<T> getInRange(List<T> list, int beginIndex, int endIndex) {

        List<T> rangedValues = new ArrayList<>();

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

    private List<Tile> getAllCardsForColor(List<Tile> tiles, Color color) {

        List<Tile> result = new ArrayList<>();

        for (Tile tile : tiles) {
            if (tile.color.equals(color)) {
                result.add(tile);
            }
        }

        return result;
    }


    public void say(String s) {
        Log.player(this.playerName, s);
    }
}
