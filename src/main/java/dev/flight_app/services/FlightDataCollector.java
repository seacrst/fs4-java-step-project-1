package dev.flight_app.services;

public class FlightDataCollector implements DataCollector<FlightDataCollector> {

    private String destination;
    private String departureDate;
    private String seatsAmount;

    public String getDepartureDate() {
        return departureDate;
    }

    public String getDestination() {
        return destination;
    }

    public String getSeatsAmount() {
        return seatsAmount;
    }

    void setDestination(String d) {
        destination = d;
    }

    void setDepartureDate(String dd) {
        departureDate = dd;
    }

    void setSeatsAmount(String sts) {
        seatsAmount = sts;
    }

    @Override
    public FlightDataCollector collect(String str) {
        if (destination.isEmpty()) setDestination(str);
        if (departureDate.isEmpty()) setDepartureDate(str);
        if (seatsAmount.isEmpty()) setSeatsAmount(str);
        return this;
    }
}
