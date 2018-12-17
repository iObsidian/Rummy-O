package game.set;

import game.tile.Color;
import game.tile.Tile;

import java.util.List;

/**
 * A group is made from three or more same-value tiles in distinct colors. For example: red 3, blue 3, black 3 and orange 3.
 */
public class GroupSet extends Set {

    public GroupSet(List<Tile> tiles) {
        super(tiles);
    }

    @Override
    public boolean isValidSet() {

        Tile previousTile = null;

        for (Tile currentTile : tiles) {
            if (previousTile != null) {

                int previousNumber = previousTile.ID;
                Color previousColor = previousTile.color;

                if (!(currentTile.ID == previousNumber && !currentTile.color.equals(previousColor))) {
                    return false;
                }
            }

            previousTile = currentTile;

        }

        return true;

    }
}
