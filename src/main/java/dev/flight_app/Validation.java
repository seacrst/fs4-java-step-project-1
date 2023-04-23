package dev.flight_app;

import dev.flight_app.controllers.FlightController;
import dev.flight_app.entities.Airline;
import dev.flight_app.entities.City;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.stream.Stream;

public class Validation {
    public boolean isValidName(String s) {
        if (s.length() < 2) return false;
        return s.chars().allMatch(Character::isLetter);
    }

    public static boolean isValidPassword(String s) {
        if (s.length() < 6) return false;
        return s.chars().anyMatch(Character::isDigit) && s.chars().anyMatch(Character::isLowerCase)
                && s.chars().anyMatch(Character::isUpperCase);
    }

    public static boolean isValidLogin(String s) {
        if (s.length() > 2 && s.length() < 20) return false;
        return s.chars().anyMatch(Character::isLetter) && s.chars().anyMatch(Character::isLetterOrDigit);
    }

    public static String Login(String s) {
        while (!isValidLogin(s)) {
            Console.println("Try again");
            s = ScannerConstrained.nextLine();
        }
        return s;
    }

    public boolean isValidPassengerQt(String s) {
        return s.chars().allMatch(c -> Character.isDigit(c) && c != '0');
    }

    public static String Pass(String s) {
        while (!isValidPassword(s)) {
            Console.println("Try again");
            s = ScannerConstrained.nextLine();
        }
        return s;
    }

    public boolean isValidId(String id) {
        return id.chars().allMatch(Character::isDigit) && id.equals("0");
    }

    public boolean isValidAirline(String s) {
        for (Airline airline : Airline.values()) {
            if (airline.toString().equals(s.toUpperCase())) return true;
        }
        return false;
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
    public static boolean validateSeatsQuantity(String s) {
        try {
            int seatsQuantity = Integer.parseInt(s);
            return seatsQuantity >= 0 && seatsQuantity <= 400;
        } catch (IllegalArgumentException ex) {
            System.out.println("Incorrect number of seats.");
            return false;
        }
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
