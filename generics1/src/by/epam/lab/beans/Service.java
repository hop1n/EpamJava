package by.epam.lab.beans;

import by.epam.lab.services.RoundMethod;

public class Service {
    String name;
    Byn totalCost;
    int usersNumber;

    public Byn getPrice(){
        return totalCost.mul(1.0/usersNumber, RoundMethod.CEIL, 0);
    }
}
