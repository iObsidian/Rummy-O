package game.set;

import game.GameTweaks;
import game.tile.Color;
import game.tile.Tile;

import java.util.List;

/**
 * A run is composed of three or more, same-colored tiles, in consecutive number order. (A 1 may not follow a 13.)
 */
public class RunSet extends Set {

    final Color color;
    final int ID;

    public RunSet(List<Tile> tiles, Color c, int ID) {
        super(tiles);

        this.color = c;
        this.ID = ID;
    }

    @Override
    public boolean isValidSet() {

        Tile previousTile = null;

        for (Tile currentTile : tiles) {

            if (previousTile != null) {

                int previousID = previousTile.ID;
                Color previousColor = previousTile.color;

                boolean isSameColor = currentTile.color.equals(previousColor);
                boolean isIncremental = false;

                if (previousID == 13 && GameTweaks.ONE_CAN_FOLLOW_THIRTEEN) {
                    isIncremental = (currentTile.ID == 1);
                } else {
                    isIncremental = (previousID + 1) == currentTile.ID;
                }

                if (!isIncremental || !isSameColor) {
                    return false;
                }
            }

            previousTile = currentTile;

        }

        return true;
    }
}
