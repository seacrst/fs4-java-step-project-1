package dev.flight_app.services;

public class PassengerDataCollector implements DataCollector<PassengerDataCollector> {
    private String name;
    private String surname;

    public void setName(String n) {
        name = n;
    }
    public void setSurname(String sn) {
        surname = sn;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public PassengerDataCollector collect(String str) {
        if (name.isEmpty()) setName(str);
        if (surname.isEmpty()) setSurname(str);
        return this;
    }

    @Override
    public void validate(String data) {

    }
}
