package game;

/**
 * Games often have deviation from the formal rules.
 * This class represent a way to configure those settings to match the accepted rules.
 */
public class GameTweaks {

    /**
     * According to Wikipedia, players have 14 or 16 tiles initially.
     */
    public static final int AMOUNT_OF_STARTING_TILES = 7;

    /*
     * According to Wikipedia, A 1 may not follow a 13.
     */
    public static final boolean ONE_CAN_FOLLOW_THIRTEEN = true;

    /**
     * According to Wikipedia, A joker can be retrieved from a set by replacing it with a tile of the same numerical value and colour it represents.
     */
    public static boolean IGNORE_JOKERS_COLOR = true;

}
