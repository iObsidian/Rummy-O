package game.tile;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Tile {

    public final Color color;

    /**
     * 0 = Special ''Face''
     * 1 to 13 are the numbers
     */
    public final int ID;

    /* Used to differentiate between this tile and the other tile of the same color and id */
    public final boolean isTwin;

    public Tile(Color color, int id) {
        this.color = color;
        this.ID = id;
        this.isTwin = false;
    }

    public Tile(Color color, int id, boolean isTwin) {
        this.color = color;
        this.ID = id;
        this.isTwin = isTwin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return ID == tile.ID &&
                color == tile.color && isTwin == tile.isTwin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, ID, isTwin);
    }

    @Override
    public String toString() {
        return ID + " " + color;
    }

}
