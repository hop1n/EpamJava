package by.epam.lab;

import by.epam.lab.exceptions.NegativeArgumentException;

import java.util.Scanner;

public final class Byn implements Comparable<Byn> {
    private int value;

    public Byn() {
        value = 0;
    }

    public Byn(int rubs, int kops) {
        if (kops > 0 && kops < 100) {
            if (rubs > 0) {
                throw new IllegalArgumentException("invalid value of rubs and kops");
            }
        }
        this.value = rubs * 100 + kops;
    }

    public Byn(String strKops) {
        this(Integer.parseInt(strKops));
    }

    public Byn(int value) {
        if (value < 0) {
            throw new NegativeArgumentException("wrong value for Byn: " + value);
        }
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
