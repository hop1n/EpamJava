import by.epam.lab.*;
import by.epam.lab.comparators.PurchaseComparator;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import static org.junit.Assert.*;

public class RunnerTest {
    final PurchaseComparator COMPARATOR = new PurchaseComparator();
    final String SEPARATOR = File.separator;
    final String IN_PATH = "src" + SEPARATOR + "in.csv";
    Scanner scanner = new Scanner(System.in);
    PurchasesList testPurchases = new PurchasesList(IN_PATH);
    PurchasesList testPurchases2 = new PurchasesList(IN_PATH);

    @Test
    public void testPurchasesListConstructor() {
        final int EXPECTED_SIZE_OF_PURCHASES = 8;
        final String EXPECTED_OUT = "candy;0;2 -> non positive value 0 in price\n" +
                "candy;-100;-2 -> non positive value -100 in price\n" +
                "candy;100;2;0 -> non positive value 0 in discount\n" +
                "candy -> wrong number of arguments\n" +
                "candy;100;2;500-> the total cost of this purchase non positive\n" +
                "candy;100;2;100-> the total cost of this purchase non positive\n" +
                "water;70;5;-1 -> non positive value -1 in discount\n" +
                ";; -> wrong number of arguments\n" +
                "water;ok;4-> wrong fields number\n" +
                "water;70;4;0.5-> wrong fields number\n" +
                "water;70.5;1-> wrong fields number"; // записать все в одну строку через nextLine
        //Assert.assertEquals(EXPECTED_OUT, scanner.);
        Assert.assertEquals(testPurchases.getPurchases().size(), EXPECTED_SIZE_OF_PURCHASES);
    }

    @Test
    public void testInsertByIndex() {
        final int EXPECTED_INDEX = 2;
        Purchase purchase = new PriceDiscountPurchase("water", "120", "10", "20");
        testPurchases.insertByIndex(EXPECTED_INDEX, purchase);
        Assert.assertEquals(testPurchases.getPurchases().indexOf(purchase), EXPECTED_INDEX);
    }

    @Test
    public void testDeleteByIndexes() {
        Purchase[] requiredPurchases = {
                testPurchases.getPurchases().get(0),
                testPurchases.getPurchases().get(1)
        };
        testPurchases.deleteByIndexes(0, 1);
        Assert.assertFalse(testPurchases.getPurchases().contains(requiredPurchases[0]));
        Assert.assertFalse(testPurchases.getPurchases().contains(requiredPurchases[1]));
    }

    @Test
    public void testGetCost(){
        final Byn EXPECTED_COST = new Byn(4692);
        Assert.assertEquals(EXPECTED_COST, testPurchases.getCost());
    }

    @Test
    public void testPurchasesSort(){
        testPurchases2.getPurchases().sort(COMPARATOR);
        testPurchases.purchaseSort();
        Assert.assertEquals(testPurchases.getPurchases(), testPurchases2.getPurchases());
    }

    @Test
    public void testSearchAnElement(){
        Purchase requiredPurchase = new PriceDiscountPurchase("meat","1100","2","80");
        int index = Collections.binarySearch(testPurchases.getPurchases(), requiredPurchase, COMPARATOR);
        int index2 = testPurchases2.searchAnElement(requiredPurchase);
        Assert.assertEquals(index, index2);

    }
}