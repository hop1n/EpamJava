package by.epam.lab.DAO;

import by.epam.lab.Result;

import java.io.Closeable;

public interface ResultDao extends Closeable {
    Result nextResult();

    boolean hasResult();

    int getResultType();
}
