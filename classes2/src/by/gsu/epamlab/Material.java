package by.gsu.epamlab;

public class Material {
    private String name;
    private final double DENSITY;
    private static final String DELIMITER = ";";

    public Material(String name, double density){
        this.name = name;
        this.DENSITY = density;
    }

    public Material(){
        this("",0);
    }

    public String getName() {
        return name;
    }

    public double getDensity() {
        return DENSITY;
    }

    @Override
    public String toString() {
        return name + DELIMITER + DENSITY;
    }
}
