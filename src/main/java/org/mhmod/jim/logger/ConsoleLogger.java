package org.mhmod.jim.logger;

public class ConsoleLogger implements JimLogger{


    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private static final String prefix = "";

    @Override
    public void write_error(String message) {
        String tag = prefix+"e/";
        System.out.println(ANSI_RED+tag+message+ANSI_RESET);
    }

    @Override
    public void write_debug(String message) {
        String tag = prefix+"d/";
        System.out.println(ANSI_WHITE+tag+message+ANSI_RESET);
    }

    @Override
    public void write_warning(String message) {
        String tag = prefix+"w/";
        System.out.println(ANSI_BLUE+tag+message+ANSI_RESET);
    }
}
