package by.epam.lab.beans;

import by.epam.lab.Constants;
import by.epam.lab.services.RoundMethod;

public class Service implements Priceable {
    String name;
    Byn totalCost;
    int usersNumber;

    public Service(){
        this(Constants.EMPTY_LINE, new Byn(0), 0);
    }

    public Service(String name, Byn totalCost, int usersNumber){
        this.name = name;
        this.totalCost = totalCost;
        this.usersNumber = usersNumber;
    }

    public Byn getPrice(){
        return totalCost.mul(1.0/usersNumber, RoundMethod.CEIL, 0);
    }

    @Override
    public String toString () {
        return name + Constants.DELIMITER + totalCost + Constants.DELIMITER + usersNumber;
    }
}
