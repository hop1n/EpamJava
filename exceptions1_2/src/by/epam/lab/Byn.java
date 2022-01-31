package by.epam.lab;

import by.epam.lab.exceptions.NegativeArgumentException;
import by.epam.lab.exceptions.NonPositiveArgumentException;

import java.util.Scanner;

public final class Byn implements Comparable<Byn> {
    private final int value;

    public Byn() {
        this(0);
    }

    public Byn(int rubs, int kops) {
        if (kops >= 0 && kops < Constants.BYN_VALUE_IN_KOPS && rubs >= 0) {
            this.value = rubs * Constants.BYN_VALUE_IN_KOPS + kops;
        } else {
            throw new IllegalArgumentException("invalid value of rubs and kops");
        }
    }



    public Byn(String strKops) {
        this(Integer.parseInt(strKops));
    }

    public Byn(int value) {
        this.value = value;
    }


    public Byn(Scanner sc) {
        this(sc.nextInt());
    }

    public Byn(Byn byn) {
        this(byn.value);
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
    public String toString() {
        return String.format(Constants.BYN_TO_STRING, value / Constants.BYN_CONVERT, value % Constants.BYN_CONVERT);
    }

}
