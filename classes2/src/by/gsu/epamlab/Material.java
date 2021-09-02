package by.gsu.epamlab;

public enum Material {
        STEEL(7850.0), COOPER(8500.0);
        private final double DENSITY;

        Material(double density){
            this.DENSITY = density;
        }

        Material() {
            this.DENSITY = 0.0;
        }

        public String getName() {
            return name().toLowerCase();
        }

        public double getDensity(){
            return DENSITY;
        }

        @Override
        public String toString() {
            return getName() + ";" + DENSITY;
        }

}
