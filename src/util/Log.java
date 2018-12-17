package util;

public class Log {

    public static void debug(String debug) {

    }

    public static void game(String s) {
        System.out.println("> [game.Game] " + s);
    }

    public static void player(String name, String s) {
        System.out.println("[" + name + "] " + s);
    }
}
