import dev.flight_app.controllers.UserController;
import dev.flight_app.services.UserService;
import dev.flight_app.entities.Booking;
import dev.flight_app.entities.Flight;
import dev.flight_app.entities.Passenger;
import dev.flight_app.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static dev.flight_app.entities.Airline.RYANAIR;
import static dev.flight_app.entities.City.BERN;
import static dev.flight_app.entities.City.BRATISLAVA;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserControllerTest {
    private UserService USMock;
    private UserController UC;
    private User user;
    private List<Passenger> passenger;
    @BeforeEach
    void setUp(){
//        USMock = mock(UserService.class);
        UC = UserController.create();
        user = new User(1, "test", "password", "Nina", "Smith");
        passenger = new ArrayList<>();
        passenger.add(new Passenger("Nina", "Smith"));
    }
    @Test
    public void testCreateNewUser(){
        User expected = user;
        when(USMock.createNewUser("test","password", "Nina", "Smith")).thenReturn(expected);

        User actual = UC.createNewUser("test","password", "Nina", "Smith");
        assertEquals(expected, actual);
        assertNotNull(actual);
        assertEquals(user.getLogin(), actual.getLogin());
        assertEquals(user.getName(), actual.getName());
        assertEquals(user.getSurname(), actual.getSurname());
    }
    @Test
    public void testCheckLogin(){
        when(USMock.checkLogin("test")).thenReturn(true);

        boolean result = UC.checkLogin("test");
        assertTrue(result);
    }
    @Test
    public void testMyBookings(){
        User expected = user;
        Flight flight = new Flight("code", RYANAIR, 100, BERN, BRATISLAVA, LocalDateTime.now(), LocalDateTime.now());
        Booking booking1 = new Booking(1, flight, passenger, user);
        Booking booking2 = new Booking(2, flight, passenger, user);
        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking1);
        bookings.add(booking2);
        when(USMock.myBookings("test")).thenReturn(bookings);

        List<Booking> result = UC.myBookings("test");
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(booking1.getId(), result.get(0).getId());
        assertEquals(booking2.getId(), result.get(1).getId());
    }

    @Test
    public void testGetUser(){
        when(USMock.getUser("test")).thenReturn(Optional.of(user));
        when(USMock.getUser("test1")).thenReturn(Optional.of(user));
        Optional<User> actual = UC.getUser("test");

        assertTrue(actual.isPresent());
        assertEquals(user.getLogin(), actual.get().getLogin());
        assertEquals(user.getName(), actual.get().getName());
        assertEquals(user.getSurname(), actual.get().getSurname());
    }
    @Test
    public void testLoadData() {
        UC.loadData();
        verify(USMock).loadData();
    }

    @Test
    public void testSaveData(){
        boolean expect = true;
        when(UC.saveData()).thenReturn(expect);
        boolean actual = UC.saveData();
        verify(USMock, times(1)).saveData();
        assertTrue(actual);
    }
    @Test
    public void testLogIn(){
        when(USMock.logIn("test", "password")).thenReturn(Optional.of(user));
        User user1 = UC.logIn("test", "password");
        assertNotNull(user1);
    }
    @Test
    public void testLogout(){
        when(USMock.logout(user)).thenReturn(true);
        boolean result = UC.logout(user);

        assertTrue(result);
        verify(USMock, times(1)).logout(user);
    }
}
