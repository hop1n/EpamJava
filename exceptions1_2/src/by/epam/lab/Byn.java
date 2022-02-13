package by.epam.lab;

public final class Byn implements Comparable<Byn> {
    private final int value;

    public Byn() {
        this(0);
    }

    public Byn(int rubs, int coins) {
        this(getValidValue(rubs, coins));
    }

    public Byn(int value) {
        if (value < 0) {
            throw new IllegalArgumentException(Causes.WRONG_BYN_VALUE + value);
        }
        this.value = value;
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

    private static int getValidValue(int rubs, int coins) {
        if (coins < 0 || coins >= Constants.BYN_VALUE_IN_KOPS || rubs < 0) {
            throw new IllegalArgumentException(Causes.RUBS_KOPS_EXC);
        }
        return Constants.BYN_VALUE_IN_KOPS * rubs + coins;
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
