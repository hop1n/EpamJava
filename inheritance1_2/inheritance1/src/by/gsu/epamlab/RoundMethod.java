package by.gsu.epamlab;

public enum RoundMethod {
    ROUND {
        public double  roundFunction(double d) {
            return Math.round(d);
        }
    },
    FLOOR {
        public double  roundFunction(double d) {
            return Math.floor(d);
        }
    },
    CEIL {
        public double  roundFunction(double d) {
            return Math.ceil(d);
        }
    };

    abstract double roundFunction(double value);
    int round(double roundedValue, RoundMethod roundMethod, int d) {
        int tenPow = (int) Math.pow(10, d);
        int result = (int) roundMethod.roundFunction(roundedValue / tenPow) * tenPow;
        return result;

    }
}
