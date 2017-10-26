package com.tigerspike;

/**
 * Tiny logger.
 */
public class Log {

    /**
     * Logs object to standard out.
     *
     * @param ob any object
     * */
    public static void say(Object ob) {
        System.out.println(YELLOW + ob + RESET);
    }

    /**
     * Highlights object at standard out.
     *
     * @param ob any object
     * */
    public static void shout(Object ob) {
        System.out.println(RED_BOLD + ob + RESET);
    }

    private static final String RESET = "\033[0m";
    private static final String YELLOW = "\033[0;33m";
    private static final String RED_BOLD = "\033[1;31m";

}
