package dev.flight_app.Database;

import dev.flight_app.entity.User;

import java.io.File;
import java.util.Map;

public class UserDB implements DB<String,User>{
    private static UserDB UDB;
    private final File file =  new File("./DB", "users.txt");
    private UserDB(){}
    public static UserDB getUDB(){
        if(UDB == null) UDB = new UserDB();
        return UDB;
    }
    @Override
    public boolean write(Map<String, User> B) {
        return write(B, file);
    }

    @Override
    public Map<String, User> read() {
        return read(file);
    }
}
