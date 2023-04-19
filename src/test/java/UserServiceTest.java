import dev.flight_app.Dao.UserDao;
import dev.flight_app.Service.UserService;
import dev.flight_app.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
    UserDao UD;
    UserService US;
    @BeforeEach
    void setUp(){
        UD = new UserDao();
        US = new UserService(UD);
    }

    @Test
    public void testGetAllUsers(){
        Map<String, User> result = US.getAllUsers();
        assertTrue(result.isEmpty());
        assertNotNull(result);
    }
    @Test
    public void testGetUser(){
        User user = US.createNewUser("xxx", "qwert1234", "Nina", "Smith");
        Optional<User> res = US.getUser(user.id());
        assertTrue(res.isPresent());
        assertEquals(user, res.get());
    }
    @Test
    public void testCreateNewUser(){
        User user = US.createNewUser("xxx", "qwert1234", "Nina", "Smith");
        User user1 =  US.createNewUser("xxx1", "qwert1234", "Nik", "Smith");
        User user2 =  US.createNewUser("xxx2", "qwert1234", "Peter", "Smith");
        assertEquals(user, US.getUser("xxx").orElse(null));
        assertEquals(user2, US.getUser("xxx2").orElse(null));
    }

    @Test
    public void checkLogin(){
        User user = US.createNewUser("xxx", "qwert1234", "Nina", "Smith");
        User user1 =  US.createNewUser("xxx1", "qwert1234", "Nik", "Smith");
        boolean res = US.checkLogin("xxx");
        boolean res2 = US.checkLogin("xxx3");
        assertFalse(res2);
        assertTrue(res);
    }
    @Test
    public void testLogIn(){
        User user = US.createNewUser("xxx", "qwert1234", "Nina", "Smith");
        Optional<User> user1 = US.logIn("xxx", "qwert1234");
        Optional<User> user3 = US.logIn("xx", "qwert1234");
        Optional<User> user2 = US.logIn("xxx", "qwert234");
        assertTrue(user1.isPresent());
        assertTrue(user3.isEmpty());
        assertTrue(user2.isEmpty());

    }
    @Test
    public void testLogout(){
        User user = US.createNewUser("xxx", "qwert1234", "Nina", "Smith");
        Optional<User> user1 = US.logIn("xxx", "qwert1234");

        assertTrue(US.logout(user));
    }
    @Test
    public void testGetNextId(){
        User user = US.createNewUser("xxx", "qwert1234", "Nina", "Smith");
        User user1 =  US.createNewUser("xxx1", "qwert1234", "Nik", "Smith");
        Integer id = user.getId();
        assertEquals(id+1, user1.getId());
    }

    @Test
    public void testSaveData(){
        User user = US.createNewUser("xxx", "qwert1234", "Nina", "Smith");
        boolean result = US.saveData();
        assertTrue(result);
        assertEquals(user, US.getAllUsers().get("xxx"));
    }
    @Test
    public void testLoadData(){
        User user = US.createNewUser("xxx", "qwert1234", "Nina", "Smith");
        boolean result = US.saveData();
        assertTrue(result);

        UserDao dao2 = new UserDao();
        UserService US2 = new UserService(dao2);
        US2.loadData();

        Map<String, User> resultBS = US.getAllUsers();
        assertEquals(1, resultBS.size());
        assertTrue(resultBS.containsKey("xxx"));
        assertEquals(user, resultBS.get(user.id()));
    }
}
