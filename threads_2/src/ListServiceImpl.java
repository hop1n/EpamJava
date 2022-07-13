import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ListServiceImpl implements StorageImplInterface {
    private final List<User> concurrentList;
    private final AtomicInteger idCounter = new AtomicInteger(0);

    public ListServiceImpl(List<User> concurrentList) {
        this.concurrentList = concurrentList;
    }

    @Override
    public User get(int id) {
        return concurrentList.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void add(String account) {
        concurrentList.add(new User(idCounter.incrementAndGet(), account));
    }
}
