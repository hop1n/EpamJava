package by.epam.lab;

public enum RoundMethod {
    FLOOR {
        double roundFun(double d) {
            return Math.floor(d);
        }
    },
    ROUND {
        double roundFun(double d) {
            return Math.round(d);
        }
    },
    CEIL {
        double roundFun(double d) {
            return Math.ceil(d);
        }
    };

    abstract double roundFun(double value);

    private final static int[] TEN_POW_ARRAY = {1, 10, 100, 1000, 10_000, 100_000, 1_000_000, 10_000_000, 100_000_000};

    public int round(double roundedValue, RoundMethod roundMethod, int d) {
        int tenPow = TEN_POW_ARRAY[d];
        int result = (int) roundMethod.roundFun(roundedValue / tenPow) * tenPow;
        return result;
    }
}
