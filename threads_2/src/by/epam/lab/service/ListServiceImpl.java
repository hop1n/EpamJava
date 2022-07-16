package by.epam.lab.service;

import by.epam.lab.interfaces.StorageImplInterface;
import by.epam.lab.bean.User;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class ListServiceImpl implements StorageImplInterface {
    private final List<User> concurrentList;
    private final AtomicInteger idCounter = new AtomicInteger(0);
    private final ReentrantLock locker  = new ReentrantLock();

    public ListServiceImpl(List<User> concurrentList) {
        this.concurrentList = concurrentList;
    }

    @Override
    public Optional<User> get(int id) {
        return concurrentList.stream()
                .filter(user -> user.getId() == id)
                .findFirst();
    }

    @Override
    public Optional<User> add(String account) {
        locker.lock();
        try {
            User userToAdd = new User(idCounter.incrementAndGet(), account);
            if (concurrentList.stream()
                    .noneMatch(user -> user.getAccount().equals(account))) {
                concurrentList.add(userToAdd);
                return Optional.of(userToAdd);
            } else {
                return Optional.empty();
            }
        } finally {
            locker.unlock();
        }
    }
}
