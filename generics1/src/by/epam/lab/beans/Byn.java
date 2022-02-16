package by.epam.lab.beans;

import by.epam.lab.Constants;
import by.epam.lab.services.RoundMethod;

public final class Byn implements Comparable<Byn> {
    private final int value;

    public Byn() {
        this(0);
    }

    public Byn(int rubs, int coins) {
        this(Constants.BYN_VALUE_IN_KOPS * rubs + coins);
    }

    public Byn(int value) {
        this.value = value;
    }

    public Byn(Byn byn) {
        this(byn.value);
    }

    public Byn mul(int count) {
        return new Byn(value * count);
    }

    public Byn mul(double k, RoundMethod roundMethod, int d) {
        return new Byn(roundMethod.round(value * k, roundMethod, d));
    }

    public Byn round(RoundMethod roundMethod, int d) {
        return new Byn(roundMethod.round(value, roundMethod, d));
    }

    public Byn div(int count) {
        return new Byn (value / count);
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
        return String.format(Constants.BYN_TO_STRING, value / Constants.BYN_VALUE_IN_KOPS, value % Constants.BYN_VALUE_IN_KOPS);
    }

}
