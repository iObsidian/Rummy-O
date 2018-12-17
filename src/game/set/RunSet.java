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

                boolean isIncremental = false;

                if (GameTweaks.ONE_CAN_FOLLOW_THIRTEEN) {
                    if (previousID == 13) { //Wraps from 13 to 1
                        isIncremental = (currentTile.ID == 1);
                    }
                }

                isIncremental = (previousID + 1) == currentTile.ID;

                if (!isIncremental || (!currentTile.color.equals(previousColor))) {
                    return false;
                }
            }

            previousTile = currentTile;

        }

        return true;
    }
}
