package by.epam.lab.service;

import by.epam.lab.interfaces.StorageImplInterface;
import by.epam.lab.bean.User;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class MapServiceImpl implements StorageImplInterface {
    private final Map<Integer, User> concurrentMap;
    private final AtomicInteger idCounter = new AtomicInteger(0);
    private final ReentrantLock locker = new ReentrantLock();

    public MapServiceImpl(Map<Integer, User> concurrentMap) {
        this.concurrentMap = concurrentMap;
    }

    @Override
    public Optional<User> get(int id) {
        return concurrentMap.values().stream().filter(user -> user.getId() == id).findFirst();
    }

    @Override
    public Optional<User> add(String account) {
        locker.lock();
        try {
            User userToAdd = new User(idCounter.incrementAndGet(), account);
            if (concurrentMap.values()
                    .stream()
                    .noneMatch(user -> user.getAccount().equals(account))) {
                concurrentMap.put(idCounter.get(), userToAdd);
                return Optional.of(userToAdd);
            } else {
                return Optional.empty();
            }
        } finally {
            locker.unlock();
        }
    }
}
