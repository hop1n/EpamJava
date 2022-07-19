package by.epam.lab.service;

import by.epam.lab.beans.User;

import java.util.Optional;

public interface StorageService {
    Optional<User> get(int id);

    Optional<User> add(String account);
}
