package by.epam.lab;

public interface ResultDao {
    Result nextResult();
    boolean hasResult();
    void closeReader();
}
