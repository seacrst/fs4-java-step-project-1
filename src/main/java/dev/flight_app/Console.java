package dev.flight_app;

import java.io.PrintStream;
import java.util.Scanner;

public class Console {
    private static final PrintStream out = System.out;
    public void println(String line) {
        out.println(line);
    }
    public static void print(String line) {
        out.print(line);
    }
}
