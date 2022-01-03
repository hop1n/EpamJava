import by.epam.lab.Purchase;
import by.epam.lab.PurchasesList;
import java.io.File;

public class Runner {
    private static void find(PurchasesList main, PurchasesList add,
                             int index) {
        Purchase purchase = add.getPurchases().get(index);
        int position = main.searchAnElement(purchase);
        System.out.print("Purchase " + purchase);
        if (position < 0) {
            System.out.println(" isn't found");
        } else {
            System.out.println(" is found at position " + position);
        }
    }

    public static void main(String[] args) {
        final String SEPARATOR = File.separator;
        final String IN_PATH = "src" + SEPARATOR + "in.csv";
        PurchasesList inPurchases = new PurchasesList(IN_PATH);
        inPurchases.printPurchases();
    }


}
