package user;

import message.Message;

import java.util.ArrayList;
import java.util.List;

public class User extends Thread {
    public String name;

    public User(String name) {
        this.name = name;
    }

    public String getUserName(){
        return name;
    }

    @Override
    public void run() {
        System.out.println("User " + name + " started");
    }
}
