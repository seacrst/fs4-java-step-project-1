import dev.flight_app.dao.UserDao;
import dev.flight_app.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class UserDaoTest {
    UserDao dao;

    @BeforeEach
    void setUp(){
        dao = new UserDao();
    }
    @Test
    public void testGetAll(){
        Map<String, User> result = dao.getAll();
        assertTrue(result.isEmpty());
        assertNotNull(result);
    }

    @Test
    public void testSave(){
        assertEquals(0, dao.getAll().size());
        User user = new User(1, "xxx", "qwert1234", "Nina", "Smith");
        dao.save(user);
        assertEquals(1, dao.getAll().size());
    }

    @Test
    public void testGetById(){
        User user = new User(1, "xxx", "qwert1234", "Nina", "Smith");
        dao.save(user);
        Optional<User> res = dao.getById("xxx");
        assertTrue(res.isPresent());
        assertEquals(user, res.get());
    }
    @Test
    public void testDelete(){
        User user = new User(1, "xxx", "qwert1234", "Nina", "Smith");
        dao.save(user);
        assertEquals(1, dao.getAll().size());
        boolean res = dao.delete("xxx");
        assertTrue(res);
        Optional<User> delB = dao.getById("xxx");
        assertFalse(delB.isPresent());
    }


    @Test
    public void testSaveToFile(){
        User user = new User(1, "xxx", "qwert1234", "Nina", "Smith");
        dao.save(user);
        boolean result = dao.save();
        assertTrue(result);
    }

    @Test
    public void testLoad(){
        User user = new User(33, "xxx", "qwert1234", "Nina", "Smith");
        dao.save(user);
        boolean saveRes = dao.save();
        assertTrue(saveRes);

        UserDao dao2 = new UserDao();
        dao2.load();
        Map<String, User> resultDao = dao.getAll();
        assertEquals(1, resultDao.size());
        assertTrue(resultDao.containsKey("xxx"));
        assertEquals(user, resultDao.get("xxx"));
    }
}
