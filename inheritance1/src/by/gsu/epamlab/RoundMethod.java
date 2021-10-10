package by.gsu.epamlab;

public enum RoundMethod {
    ROUND {
        public double roundFunction(double d) {
            return Math.round(d);
        }
    },
    FLOOR {
        public double roundFunction(double d) {
            return Math.floor(d);
        }
    },
    CEIL {
        public double roundFunction(double d) {
            return Math.ceil(d);
        }
    };
    private final static int[] POW_ARR = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000};

    abstract double roundFunction(double value);

    public int round(double roundedValue, RoundMethod roundMethod, int d) {
        int tenPow = POW_ARR[d];
        int result = (int) roundMethod.roundFunction(roundedValue / tenPow) * tenPow;
        return result;

    }
}
