package Entity;

import java.io.Serializable;

public class Passenger implements Serializable {
    private static final long passengerUID = 1_0L;
    private static int passengerCounter = 1;

    private final int id;
    private final String firstName;
    private final String lastName;

    public Passenger(String firstName, String lastName) {
        this.id = passengerCounter++;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Passenger(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Passenger)) return false;

        Passenger passenger = (Passenger) o;

        if (id != passenger.id) return false;
        if (!firstName.equals(passenger.firstName)) return false;
        return lastName.equals(passenger.lastName);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        return result;
    }
}
