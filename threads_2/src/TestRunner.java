import by.epam.lab.service.MapServiceImpl;
import by.epam.lab.interfaces.StorageImplInterface;
import by.epam.lab.bean.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

import static by.epam.lab.Constants.Constants.INTERRUPT_EXCEPTION;

public class TestRunner {
    private final List<User> usersList = new CopyOnWriteArrayList<>();
    private final Map<Integer, User> usersMap = new ConcurrentHashMap<>();

    @Test
    public void listTestWithEmpty() {
        String[] accounts = {"Chet Hooper", "Chet Hooper", "Chet Hooper", "Chet Hooper",
                "Chet Hooper", "Chet Hooper", "Chet Hooper", "Chet Hooper", "Chet Hooper",
                "Chet Hooper"};
        int usersNumber = 1;
        addTenAccounts(accounts);
        Assert.assertEquals(usersNumber, usersMap.size());
    }

    @Test
    public void listTestWithTen() {
        String[] accounts = {"Alex Zinchenko", "Lenny Hope", "Betty Bards", "Billie Brown",
                "Chet Hooper", "Spencer Cooper", "Sam Kolt", "Lenny Penny", "Rose Bell",
                "Chloe Moriondo"};
        int usersNumber = 10;
        addTenAccounts(accounts);
        Assert.assertEquals(usersNumber, usersMap.size());
    }

    @Test
    public void listTestWithNonUniq() {
        String[] accounts = {"Alex Zinchenko", "Lenny Hope", "Betty Bards", "Billie Brown",
                "Chet Hooper", "Chet Hooper", "Chet Hooper", "Chet Hooper", "Chet Hooper",
                "Chet Hooper"};
        int usersNumber = 5;
        addTenAccounts(accounts);
        Assert.assertEquals(usersNumber, usersMap.size());
    }

    private void addTenAccounts(String[] accounts) {
        StorageImplInterface service = new MapServiceImpl(usersMap);
        CountDownLatch latch = new CountDownLatch(accounts.length);
        for (String s : accounts) {
            Random random = new Random();
            new Thread(() -> {
                service.add(s);
                service.get(random.nextInt() + 1);
                latch.countDown();
            }).start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            System.out.println(INTERRUPT_EXCEPTION);
            Thread.currentThread().interrupt();
        }
    }
}
