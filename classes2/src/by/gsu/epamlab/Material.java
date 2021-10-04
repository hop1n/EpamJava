package by.gsu.epamlab;

public class Material {
    private final String name;
    private final double DENSITY;

    public Material(String name, double density){
        this.name = name;
        this.DENSITY = density;
    }

    public Material(){
        this("",0.0);
    }

    public String getName() {
        return name;
    }

    public double getDensity() {
        return DENSITY;
    }

    @Override
    public String toString() {
        return name + ";" + DENSITY;
    }
}
