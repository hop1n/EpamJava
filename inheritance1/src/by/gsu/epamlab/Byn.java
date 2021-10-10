package by.gsu.epamlab;

import java.util.Scanner;

public class Byn implements Comparable<Byn> {
    private int value;

    public Byn(int value) {
        this.value = value;
    }

    public Byn(Byn b) {
        this(b.value);
    }

    public Byn() {
        value = 0;
    }

    public Byn(Scanner sc) {
        this(sc.nextInt());
    }

    public Byn mul(double k, RoundMethod roundMethod, int d) {
        value = roundMethod.round(value * k, roundMethod, d);
        return this;
    }

    public Byn round(RoundMethod roundMethod, int d) {
        value = roundMethod.round(value, roundMethod, d);
        return this;
    }

    public Byn subtract(Byn a) {
        value -= a.value;
        return this;
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
        return String.format("%d.%02d", value / 100, value % 100);
    }

}
