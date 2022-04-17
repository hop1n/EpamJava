package by.epam.lab;

import java.io.Closeable;

public interface ResultDao extends Closeable {
    Result nextResult();
    boolean hasResult();
}
