package game.set;

import game.tile.Color;
import game.tile.Tile;

import java.util.*;

/*
 *  A list of tiles.
 *
 *  Represents the game deck and player hands.
 *  See GroupSet and RunSet for possible game sets.
 */
public class Set implements Iterable<Tile> {

    final Stack<Tile> tiles;

    public Set() {
        this.tiles = new Stack<>();
    }

    public Set(List<Tile> tiles) {
        this.tiles = new Stack<>();
        this.tiles.addAll(tiles);
    }

    @Override
    public Iterator<Tile> iterator() {
        return tiles.iterator();
    }

    /* Utility method to Add game.tile.Tile at specified index */
    public void add(int index, Tile tile) {
        tiles.add(index, tile);
    }

    public void add(Tile tile) {
        tiles.add(tile);
    }

    // Utility method to Add game.tile.Tile at end  */
    public void append(Tile tile) {
        tiles.add(tiles.size(), tile);
    }

    // Utility method to Add game.tile.Tile at beginning  */
    public void push(Tile tile) {
        tiles.push(tile);
    }

    public Tile remove(int index) {
        if (tiles.get(index) == null) {
            System.err.println("Error, card at index " + index + " does not exist in this sequence.");
        }
        return tiles.remove(index);
    }

    public List<Tile> getFaceCards() {
        List<Tile> faceTiles = new ArrayList<>();
        for (Tile c : tiles) {
            if (c.ID == 0) {
                faceTiles.add(c);
            }
        }
        return faceTiles;
    }

    public boolean containsColor(Color c) {

        for (Tile t : tiles) {
            if (t.color == c) {
                return true;
            }
        }

        return false;
    }

    public boolean isValidSet() {
        return true;
    }

    public int size() {
        return tiles.size();
    }

    public void shuffle() {
        Collections.shuffle(tiles);
    }

    public static final Comparator<Tile> sortByID = Comparator.comparingInt(o -> o.ID);

    public Set sortByID() {
        tiles.sort(sortByID);
        return this;
    }

    public Tile get(int index) {
        return tiles.get(index);
    }

    public Set getAllCardsForColor(Color color) {
        Set result = new Set();
        for (Tile tile : tiles) {
            if (tile.color.equals(color)) {
                result.add(tile);
            }
        }
        return result;
    }

    @Override
    public String toString() {

        StringBuilder s = new StringBuilder();

        for (Tile tile : tiles) {
            s.append("[").append(tile).append("],");
        }

        return s.toString();

    }

}
