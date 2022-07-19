package by.epam.lab.beans;

import static by.epam.lab.Constants.Constants.*;

public class User {
    private final int id;
    private final String account;

    public User(int id, String account) {
        this.id = id;
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }

    @Override
    public String toString() {
        return id + DELIMITER + account;
    }
}
