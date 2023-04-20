package dev.flight_app.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import dev.flight_app.events.Actions;

public class Console {
    private static final Scanner scanner = new Scanner(System.in);

    protected Console() {
    }

    public static String input() {
        return scanner.nextLine().trim();
    }

    public static void output(String str) {
        System.out.println(str);
    }

    public static String formatRows(Actions... actions) {
        return Arrays.stream(actions)
                .map(a -> "\n" + a.get() + "\n")
                .collect(Collectors.joining());
    }

    public static String formatRows(ArrayList<Actions> actions) {
        return actions.stream()
                .map(a -> "\n" + a.get() + "\n")
                .collect(Collectors.joining());
    }


}
