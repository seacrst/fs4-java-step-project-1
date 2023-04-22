package dev.flight_app;

import dev.flight_app.entities.Airline;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Validation {
    public boolean isValidName(String s){
        if (s.length() < 2) return false;
        return s.chars().allMatch(Character::isLetter);
    }
    public static boolean isValidPassword(String s){
        if (s.length() < 6) return false;
        return s.chars().anyMatch(Character::isDigit) && s.chars().anyMatch(Character::isLowerCase)
                && s.chars().anyMatch(Character::isUpperCase) ;
    }
    public static boolean isValidLogin(String s){
        if (s.length() > 2 && s.length() < 20) return false;
        return s.chars().anyMatch(Character::isLetter) && s.chars().anyMatch(Character::isLetterOrDigit);
    }
    public static String Login(String s){
        while(!isValidLogin(s)){
            Console.println("Try again");
            s = ScannerConstrained.nextLine();
        }
        return s;
    }

    public boolean isValidPassengerQt(String s){
        return s.chars().allMatch(c -> Character.isDigit(c) && c != '0');
    }
    public static String Pass(String s){
        while(!isValidPassword(s)){
            Console.println("Try again");
            s = ScannerConstrained.nextLine();
        }
        return s;
    }
    public boolean isValidId(String id){
        return id.chars().allMatch(Character::isDigit) && id.equals("0");
    }

    public boolean isValidAirline(String s){
        for(Airline airline: Airline.values()){
            if(airline.toString().equals(s.toUpperCase())) return true;
        }
        return false;
    }
    public boolean validateDate(String s){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            LocalDate date = LocalDate.parse(s, formatter);
            return true;
        } catch (DateTimeParseException ex) {
            System.out.println("Incorrect date format. The required format is \"dd-MM-yyyy\"");
            return false;
        }
    }

    public static LocalDate stringToDate (String s) {
            return LocalDate.parse(s);
    }


}
