package game.set;

import game.tile.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
   A game.tile.Tile sequence in game, of which there can be many.
 */
public abstract class Set {

    final Stack<Tile> tiles;

    public Set(List<Tile> tiles) {
        this.tiles = new Stack<>();
        this.tiles.addAll(tiles);
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    /* Utility method to Add game.tile.Tile at specified index */
    public void add(int index, Tile tile) {
        tiles.add(index, tile);
    }

    // Utility method to Add game.tile.Tile at end  */
    public void append(Tile tile) {
        tiles.add(tiles.size(), tile);
    }

    // Utility method to Add game.tile.Tile at beginning  */
    public void push(Tile tile) {
        tiles.push(tile);
    }

    public Tile removeCard(int index) {
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


    public abstract boolean isValidSet();


}
