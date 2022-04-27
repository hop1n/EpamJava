package by.epam.lab.DAO;

import by.epam.lab.beans.Result;
import by.epam.lab.services.ResultKind;

import java.io.Closeable;

public interface ResultDao extends Closeable {
    Result nextResult();

    boolean hasResult();
}
