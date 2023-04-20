package dev.flight_app;
import java.util.Scanner;
public class ScannerConstrained {
    private static final Scanner scanner = new Scanner(System.in);
    public static String nextLine() {
        return scanner.nextLine();
    }
    public static String next() {
        return scanner.next();
    }
}
