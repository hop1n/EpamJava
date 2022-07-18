package by.epam.lab.service.impl;

import by.epam.lab.beans.User;
import by.epam.lab.service.StorageService;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public abstract class AbstractImpl implements StorageService {
    private final AtomicInteger idCounter = new AtomicInteger(0);
    private final ReentrantLock locker = new ReentrantLock();

    protected AbstractImpl() {
    }

    @Override
    public Optional<User> add(String account) {
        locker.lock();
        try {
            if (getStorageImplForSearch().stream()
                    .noneMatch(user -> user.getAccount().equals(account))) {
                User userToAdd = new User(idCounter.incrementAndGet(), account);
                addUserToStorage(userToAdd);
                return Optional.of(userToAdd);
            } else {
                return Optional.empty();
            }
        } finally {
            locker.unlock();
        }
    }

    public abstract List<User> getStorageImplForSearch();

    public abstract void addUserToStorage(User userToAdd);
}
