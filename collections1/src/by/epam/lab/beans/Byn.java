package by.epam.lab.beans;

import by.epam.lab.Constants;

import java.util.Scanner;

public final class Byn implements Comparable<Byn> {
    private final int value;

    public Byn() {
        value = 0;
    }

    public Byn(int value) {
        this.value = value;
    }

    public Byn(Byn byn) {
        this(byn.value);
    }

    public Byn(Scanner sc) {
        this(sc.nextInt());
    }

    public Byn(String value){
        this(Integer.parseInt(value));
    }

    public Byn mul(int count) {
        return new Byn(value * count);
    }

    public Byn sub(Byn byn) {
        return new Byn(value - byn.value);
    }

    public Byn add(Byn byn) {
        return new Byn(value + byn.value);
    }

    @Override
    public int compareTo(Byn byn) {
        return this.value - byn.value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Byn byn = (Byn) obj;
        return value == byn.value;
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public String toString() {
        return String.format(Constants.BYN_TO_STRING, value / Constants.BYN_VALUE_IN_KOPS,
                value % Constants.BYN_VALUE_IN_KOPS);
    }

}
