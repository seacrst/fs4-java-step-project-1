package dev.flight_app;

import dev.flight_app.entities.Airline;
import dev.flight_app.entities.City;
import dev.flight_app.entities.Flight;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class FlightGenerator {

    private final Random rnd = new Random();

    public int rndByLimits(int first, int last) {
        return (int) (first + Math.random() * (last - first + 1) + 1);
    }

    public LocalDateTime dateTimeGen(int daysRange) {
        long first = LocalDate
                .now()
                .toEpochDay();
        long last = LocalDate
                .now()
                .plusDays(daysRange)
                .toEpochDay();
        long rndDay = ThreadLocalRandom.current().nextLong(first, last);

        LocalDateTime rndDayTime = LocalDateTime.of(LocalDate.ofEpochDay(rndDay),
                LocalTime.of(rnd.nextInt(24), rnd.nextInt(60) / 5 * 5));
        rndDayTime = rndDayTime.isAfter(LocalDateTime.now()) ? rndDayTime : rndDayTime.plusDays(1);

        return rndDayTime;
    }

    public Airline airlineGen() {
        return Airline.values()[rnd.nextInt(Airline.values().length)];
    }

    public String flightCodeGen(Airline airline) {
        return airline.getCode() + rndByLimits(1000, 9999);
    }

    public City cityGen() {
        return City.values()[rnd.nextInt(City.values().length)];
    }

    public Map<Integer, Flight> flightsGen(int quantity) {
        Map <Integer, Flight> flights = new HashMap<>();
        String flightCode;
        Airline airline;
        int seatsQuantity;
        City departureCity, arrivalCity;
        LocalDateTime departureDateTime, arrivalDateTime;

        for (int i = 0; i < quantity; i++) {
            airline = airlineGen();
            flightCode = flightCodeGen(airline);
            seatsQuantity = rndByLimits(0, 400);
            departureCity = cityGen();
            do arrivalCity = cityGen();
            while (departureCity.equals(arrivalCity));
            departureDateTime = dateTimeGen(30);
            arrivalDateTime = departureDateTime.plusHours(rndByLimits(1, 4)).minusMinutes(rndByLimits(0, 60) / 10 * 10);

            Flight tempFlight = new Flight(
                    flightCode,
                    airline,
                    seatsQuantity,
                    departureCity,
                    arrivalCity,
                    departureDateTime,
                    arrivalDateTime);

            flights.put(tempFlight.id(), tempFlight);
        }
        return flights;
    }
}