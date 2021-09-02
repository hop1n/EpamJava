package by.gsu.epamlab;

public enum Material {
        STEEL("steel", 7850.0), COOPER("cooper", 8500.0);
        private final String NAME;
        private final double DENSITY;

        Material(String name, double density){
            this.NAME = name;
            this.DENSITY = density;
        }

        Material() {
            this("", 0.0);
        }

        public String getName(){
            return NAME;
        }

        public double getDensity(){
            return DENSITY;
        }

        @Override
        public String toString() {
            return NAME + ";" + DENSITY;
        }

}
