package by.gsu.epamlab;

public class KopsToByn {
    public static String toByn(int kops){
        return String.format("%d.%02d", kops / 100, kops % 100);
    }
}
