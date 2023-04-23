package dev.flight_app.services;

import dev.flight_app.Validation;

import java.util.function.Function;

public class FlightDataCollector implements DataCollector<FlightDataCollector> {

    private String destination = "";
    private String departureDate = "";
    private String seatsAmount = "";

    private final Validation validator  = new Validation();

    public String getDepartureDate() {
        return departureDate;
    }

    public String getDestination() {
        return destination;
    }

    public String getSeatsAmount() {
        return seatsAmount;
    }

    public void setDestination(String d) {
        destination = d;
    }

    public void setDepartureDate(String dd) {
        departureDate = dd;
    }

    public void setSeatsAmount(String sts) {
        seatsAmount = sts;
    }

    public boolean isValidData(String data) {
        return validator.validateDate(data);
    }


    @Override
    public FlightDataCollector collect(String data) {
        if (destination.isEmpty()) setDestination(data);
        if (departureDate.isEmpty()) {
            if (isValidData(data)) {
                setDepartureDate(data);
            } else {

            }
        }
        if (seatsAmount.isEmpty()) setSeatsAmount(data);
        return this;
    }

    @Override
    public void validate(String data) {

    }

//    @Override
//    public void validate(String data) {
//        if (!departureDate.isEmpty()) {
////            isValidData(departureDate, validator);
//        }
//    }
}
