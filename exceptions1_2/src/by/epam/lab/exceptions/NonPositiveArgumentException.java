package by.epam.lab.exceptions;

public class NonPositiveArgumentException extends IllegalArgumentException {
    public NonPositiveArgumentException(String message){
        super(message);
    }
}
