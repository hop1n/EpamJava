package by.epam.lab.service.impl;

import by.epam.lab.beans.User;
import by.epam.lab.service.StorageService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class MapServiceImpl extends AbstractImpl{
    private final Map<Integer, User> concurrentMap;

    public MapServiceImpl(Map<Integer, User> concurrentMap) {
        super();
        this.concurrentMap = concurrentMap;
    }

    @Override
    public Optional<User> get(int id) {
        return Optional.ofNullable(concurrentMap.get(id));
    }

    @Override
    public List<User> getStorageImplForSearch() {
        return new ArrayList<>(concurrentMap.values());
    }

    @Override
    public void addUserToStorage(User userToAdd) {
        concurrentMap.put(userToAdd.getId(), userToAdd);
    }
}
