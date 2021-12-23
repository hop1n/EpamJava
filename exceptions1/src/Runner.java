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
        final String IN_PATH = "src" + SEPARATOR + args[0] + ".csv";
        final String ADDON_PATH = "src" + SEPARATOR + args[1] + ".csv";
        PurchasesList inPurchases = new PurchasesList(IN_PATH);
        inPurchases.printTableOfPurchases();
        PurchasesList addonPurchases = new PurchasesList(ADDON_PATH);
        inPurchases.insertByIndex
                (0, addonPurchases.getPurchases().get((addonPurchases.getPurchases().size() - 1)));
        inPurchases.insertByIndex(1000, addonPurchases.getPurchases().get(0));
        inPurchases.insertByIndex(2, addonPurchases.getPurchases().get(2));
        inPurchases.deleteByIndex(3);
        inPurchases.deleteByIndex(10);
        inPurchases.deleteByIndex(-5);
        inPurchases.printTableOfPurchases();
        inPurchases.purchaseSort(args[2]);
        inPurchases.printTableOfPurchases();
        find(inPurchases, addonPurchases, 1);
        find(inPurchases, addonPurchases, 3);
    }
}
