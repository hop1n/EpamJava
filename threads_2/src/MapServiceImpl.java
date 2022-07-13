import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MapServiceImpl implements StorageImplInterface {
    private final Map<Integer, User> concurrentMap;
    private final AtomicInteger idCounter = new AtomicInteger(0);

    public MapServiceImpl(Map<Integer, User> concurrentMap) {
        this.concurrentMap = concurrentMap;
    }

    @Override
    public User get(int id) {
        return concurrentMap.get(id);
    }

    @Override
    public void add(String account) {
        concurrentMap.put(idCounter.incrementAndGet(), new User(concurrentMap.size(), account));
    }
}
