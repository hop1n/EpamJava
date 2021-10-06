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

    public Byn multiply(int b) {
        value *= b;
        return this;
    }

    public Byn subtract(Byn a) {
        value -= a.value;
        return this;
    }

    public Byn multiply(double count) {
        value = (int) Math.round(value * count);
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
