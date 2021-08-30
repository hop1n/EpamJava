package by.gsu.epamlab;

public class Material {
    private String name;
    public final double DENSITY;

    public Material(String name, double density){
        this.name = name;
        this.DENSITY = density;
    }

    public Material(){
        DENSITY = 0;
    }

    public String getName() {
        return name;
    }

    public double getDensity() {
        return DENSITY;
    }

    @Override
    public String toString() {
        return name + ';' + DENSITY;
    }
}
