import by.epam.lab.service.impl.MapServiceImpl;
import by.epam.lab.service.StorageService;
import by.epam.lab.beans.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static by.epam.lab.Constants.Constants.INTERRUPT_EXCEPTION;

public class TestRunner {

//    if you want to test ListImpl just unComment fields below
    private final Map<Integer, User> usersMap = new ConcurrentHashMap<>();
    private final StorageService service = new MapServiceImpl(usersMap);
//    private final List<User> usersList = new CopyOnWriteArrayList<>();
//    private final StorageService service = new ListServiceImpl(usersList);

    @Test
    public void TestWithEmpty() throws InterruptedException {
        String[] accounts = {"Chet Hooper", "Chet Hooper", "Chet Hooper", "Chet Hooper",
                "Chet Hooper", "Chet Hooper", "Chet Hooper", "Chet Hooper", "Chet Hooper",
                "Chet Hooper"};
        int usersNumber = 1;
        addTenAccounts(accounts);
        Assert.assertEquals(usersNumber, usersMap.size());
    }

    @Test
    public void TestWithTenUniqAccounts() throws InterruptedException {
        String[] accounts = {"Alex Zinchenko", "Lenny Hope", "Betty Bards", "Billie Brown",
                "Chet Hooper", "Spencer Cooper", "Sam Kolt", "Lenny Penny", "Rose Bell",
                "Chloe Moriondo"};
        int usersNumber = 10;
        addTenAccounts(accounts);
        Assert.assertEquals(usersNumber, usersMap.size());
    }

    @Test
    public void TestWithNonUniq() throws InterruptedException {
        String[] accounts = {"Alex Zinchenko", "Lenny Hope", "Betty Bards", "Billie Brown",
                "Chet Hooper", "Chet Hooper", "Chet Hooper", "Chet Hooper", "Chet Hooper",
                "Chet Hooper"};
        int usersNumber = 5;
        addTenAccounts(accounts);
        Assert.assertEquals(usersNumber, usersMap.size());
    }

    @Test
    public void TestWithTwoUniqAccounts() throws InterruptedException {
        String[] accounts = {"Alex Zinchenko", "Lenny Hope"};
        int usersNumber = 2;
        addTwoAccountsWithSleep(accounts);
        Assert.assertEquals(usersNumber, usersMap.size());
    }

    @Test
    public void TestWithTwoUnUniqAccounts() throws InterruptedException {
        String[] accounts = {"Alex Zinchenko", "Alex Zinchenko"};
        int usersNumber = 1;
        addTwoAccountsWithSleep(accounts);
        Assert.assertEquals(usersNumber, usersMap.size());
    }

    @Test
    public void TestTwoDuplicatedWithPreparedStorage() throws InterruptedException {
        String[] accounts = {"Alex Zinchenko", "Alex Zinchenko"};
        int usersNumber = 3;
        service.add("Lily Potter");
        service.add("James Potter");
        addTwoAccountsWithSleep(accounts);
        Assert.assertEquals(usersNumber, usersMap.size());
    }

    @Test
    public void TestTwoUniqWithPreparedStorage() throws InterruptedException {
        String[] accounts = {"Alex Zinchenko", "Lenny Hope"};
        int usersNumber = 4;
        service.add("Lily Potter");
        service.add("James Potter");
        addTwoAccountsWithSleep(accounts);
        Assert.assertEquals(usersNumber, usersMap.size());
    }

    @Test
    public void TestTwoUnUniqWithPreparedStorage() throws InterruptedException {
        String[] accounts = {"Lily Potter", "Lenny Hope"};
        int usersNumber = 3;
        service.add("Lily Potter");
        service.add("James Potter");
        addTwoAccountsWithSleep(accounts);
        Assert.assertEquals(usersNumber, usersMap.size());
    }

    private void addTwoAccountsWithSleep(String[] accounts) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(accounts.length);
        for (String s : accounts) {
            new Thread(() -> {
                service.add(s);
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(INTERRUPT_EXCEPTION);
                }
                latch.countDown();
            }).start();
        }
        latch.await();
    }

    private void addTenAccounts(String[] accounts) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(accounts.length);
        for (String s : accounts) {
            Random random = new Random();
            new Thread(() -> {
                service.add(s);
                service.get(random.nextInt() + 1);
                latch.countDown();
            }).start();
        }
        latch.await();
    }
}