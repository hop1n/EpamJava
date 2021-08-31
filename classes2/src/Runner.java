import by.gsu.epamlab.Material;
import by.gsu.epamlab.Subject;

public class Runner {
    public static void main (String[] args){
        final Material STEEL = new Material("steel", 7850.0);
        final Material COOPER = new Material ("cooper", 8500.0);
        Subject wire = new Subject("wire", STEEL, 0.03);
        System.out.println(wire);
        wire.setMaterial(COOPER);
        System.out.println("The wire mass is " + wire.getMass()+" kg.");
    }
}
