package by.epam.lab.interfaces;

import by.epam.lab.bean.User;

import java.util.Optional;

public interface StorageImplInterface {
    public Optional<User> get(int id);

    public Optional<User> add(String account);
}
