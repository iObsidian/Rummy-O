package game.set;

import game.tile.Color;
import game.tile.Tile;

import java.util.List;

/**
 * A run is composed of three or more, same-colored tiles, in consecutive number order. (A 1 may not follow a 13.)
 */
public class RunSet extends Set {

    public RunSet(List<Tile> tiles) {
        super(tiles);
    }

    public Color getColor() {
        return tiles.get(0).color;
    }

    public int getID() {
        return tiles.get(0).ID;
    }

    @Override
    public boolean isValidSet() {

        Tile previousTile = null;

        for (Tile currentTile : tiles) {

            if (previousTile != null) {

                int previousID = previousTile.ID;
                Color previousColor = previousTile.color;

                boolean isIncremental = false;

                if (previousID == 13) { //Wraps from 13 to 1
                    isIncremental = (currentTile.ID == 1);
                } else {
                    isIncremental = (previousID + 1) == currentTile.ID;
                }

                if (!isIncremental || (!currentTile.color.equals(previousColor))) {
                    return false;
                }
            }

            previousTile = currentTile;

        }

        return true;
    }
}
