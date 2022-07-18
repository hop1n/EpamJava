package by.epam.lab.service.impl;

import by.epam.lab.beans.User;
import by.epam.lab.service.StorageService;

import java.util.List;
import java.util.Optional;

public class ListServiceImpl extends AbstractImpl{
    private final List<User> concurrentList;

    public ListServiceImpl(List<User> concurrentList) {
        super();
        this.concurrentList = concurrentList;
    }

    @Override
    public Optional<User> get(int id) {
        return concurrentList.stream()
                .filter(user -> user.getId() == id)
                .findFirst();
    }

    @Override
    public List<User> getStorageImplForSearch() {
        return concurrentList;
    }

    @Override
    public void addUserToStorage(User userToAdd) {
        concurrentList.add(userToAdd);
    }
}
