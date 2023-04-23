package dev.flight_app;

import dev.flight_app.entities.City;
import dev.flight_app.entities.Console;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Validation {
    public static boolean isValidName(String s) {
        if (s.length() < 2) return false;
        return s.chars().allMatch(Character::isLetter);
    }

    public static boolean validateDate(String s) {
        try {
            LocalDate date = LocalDate.parse(s, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            return true;
        } catch (DateTimeParseException ex) {
            System.out.println("Incorrect date format. The required format is \"dd-MM-yyyy\"");
            return false;
        }
    }

    public static boolean validateCity(String s) {
        s = stringNormalisation(s);
        try {
            City city = City.valueOf(s);
            return true;
        } catch (IllegalArgumentException ex) {
            System.out.println("Incorrect city format or this city could not be found in the database.");
            return false;
        }
    }

    public static String stringNormalisation(String s) {
        StringBuilder sb = new StringBuilder();
        s.toUpperCase()
                .codePoints()
                .filter(x -> x >= 65 && x <= 90 || x == 95)
                .mapToObj(x -> (char) x)
                .forEach(sb::append);
        return sb.toString();
    }

    public static boolean validateFlightId(String s) {
        try {
            int id = Integer.parseInt(s);
            return true;
        } catch (IllegalArgumentException ex) {
            System.out.println("Incorrect Flight ID format or this ID could not be found in the database.");
            return false;
        }
    }
    public static boolean validateNumber(String s) {
        try {
            int id = Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex) {
            Console.output("Incorrect ID format.");
            return false;
        } catch (NullPointerException ex) {
            return false;
        }
    }
    public static boolean validateSeatsQuantity(String s) {
        try {
            int seatsQuantity = Integer.parseInt(s);
            if (!(seatsQuantity > 0 && seatsQuantity <= 400)) throw new IllegalArgumentException();
            return true;
        } catch (IllegalArgumentException ex) {
            System.out.println("Incorrect number of seats.");
            return false;
        }
    }

    public static boolean isValidIndex(String s, int qt) {
        if(Validation.validateFlightId(s)){
            return Integer.parseInt(s) <= qt;
        }
        return false;
    }

    public static LocalDate stringToDate(String s) {
        return LocalDate.parse(s, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public static City stringToCity(String s) {
        s = stringNormalisation(s);
        return City.valueOf(s);
    }
    public static int stringToFlightId(String s) {
        return Integer.parseInt(s);
    }
    public static int stringToSeatsQuantity(String s) {
        return Integer.parseInt(s);
    }

}
