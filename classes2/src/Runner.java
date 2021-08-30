import by.gsu.epamlab.Material;
import by.gsu.epamlab.Subject;

public class Runner {
    public static void main (String[] args){
        final Material STEEL = new Material("Steel", 7850);
        final Material COOPER = new Material ("Cooper", 8500);
        Subject wire = new Subject("Wire", STEEL, 0.03);
        System.out.println(wire);
        wire.setMaterial(COOPER);
        System.out.println("The wire mass is " + wire.getMass() + " kg.");
    }
}
