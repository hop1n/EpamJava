package by.gsu.epamlab;

public class Material {
    private final String NAME;
    private final double DENSITY;

    public Material(String name, double density){
        this.NAME = name;
        this.DENSITY = density;
    }

    public Material(){
        this("", 0.0);
    }

    public String getName() {
        return NAME;
    }

    public double getDensity() {
        return DENSITY;
    }

    @Override
    public String toString() {
        return NAME + ";" + DENSITY;
    }
}
