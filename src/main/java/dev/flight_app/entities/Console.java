package dev.flight_app.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public abstract class Console {
    private static final Scanner scanner = new Scanner(System.in);

    protected Console() {}

    public static String input() {
        return scanner.nextLine().trim();
    }

    public static void output(String str) {
        System.out.println(str);
    }
    public static void output(Flight f) {
        System.out.println(f);
    }
    public static void output(Booking b) {
        System.out.println(b);
    }


}
