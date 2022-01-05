import by.epam.lab.*;
import by.epam.lab.comparators.PurchaseComparator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import static org.junit.Assert.*;

public class RunnerTest {
    final PurchaseComparator COMPARATOR = new PurchaseComparator();
    final String SEPARATOR = File.separator;
    final String IN_PATH = "src" + SEPARATOR + "in.csv";
    PurchasesList testPurchases;
    PurchasesList testPurchases2;
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setErr(new PrintStream(errContent));
        testPurchases = new PurchasesList(IN_PATH);
        testPurchases2 = new PurchasesList(IN_PATH);
    }

    @After
    public void restoreStreams() {
        System.setErr(originalErr);
    }

//    public static String getOutput() {
//        StringBuilder inputString = new StringBuilder();
//
//        while (.hasNextLine()){
//            inputString.append(scanner.nextLine());
//        }
//        return inputString.toString();
//    }

    @Test
    public void testPurchasesListConstructor() {
        final int EXPECTED_SIZE_OF_PURCHASES = 8;
        String EXPECTED_OUT = "candy;0;2 -> non positive value 0 in price\r\n" +
                "candy;-100;-2 -> non positive value -100 in price\r\n" +
                "candy;100;2;0 -> non positive value 0 in discount\r\n" +
                "candy -> wrong number of arguments\r\n" +
                "candy;100;2;500-> the total cost of this purchase non positive\r\n" +
                "candy;100;2;100-> the total cost of this purchase non positive\r\n" +
                "water;70;5;-1 -> non positive value -1 in discount\r\n" +
                ";; -> wrong number of arguments\r\n" +
                "water;ok;4-> wrong fields number\r\n" +
                "water;70;4;0.5-> wrong fields number\r\n" +
                "water;70.5;1-> wrong fields number\r\n" +
                "candy;0;2 -> non positive value 0 in price\r\n" +
                "candy;-100;-2 -> non positive value -100 in price\r\n" +
                "candy;100;2;0 -> non positive value 0 in discount\r\n" +
                "candy -> wrong number of arguments\r\n" +
                "candy;100;2;500-> the total cost of this purchase non positive\r\n" +
                "candy;100;2;100-> the total cost of this purchase non positive\r\n" +
                "water;70;5;-1 -> non positive value -1 in discount\r\n" +
                ";; -> wrong number of arguments\r\n" +
                "water;ok;4-> wrong fields number\r\n" +
                "water;70;4;0.5-> wrong fields number\r\n" +
                "water;70.5;1-> wrong fields number\r\n";
        Assert.assertEquals(EXPECTED_OUT, errContent.toString());
        Assert.assertEquals(testPurchases.getPurchases().size(), EXPECTED_SIZE_OF_PURCHASES);
    }

    @Test
    public void testInsertByIndex() {
        final int EXPECTED_INDEX = 2;
        final int WRONG_INDEX = -1;
        final int WRONG_SECOND_INDEX = 50;
        final int START_INDEX = 0;
        int lastIndex;
        Purchase purchase = new PriceDiscountPurchase("water", "120", "10", "20");
        Purchase second_purchase = new PriceDiscountPurchase("bread", "100", "20", "10");
        Purchase third_purchase = new PriceDiscountPurchase("apple", "50", "5", "10");
        testPurchases.insertByIndex(EXPECTED_INDEX, purchase);
        Assert.assertEquals(testPurchases.getPurchases().indexOf(purchase), EXPECTED_INDEX);
        testPurchases.insertByIndex(WRONG_INDEX, second_purchase);
        Assert.assertEquals(testPurchases.getPurchases().indexOf(second_purchase), START_INDEX);
        testPurchases.insertByIndex(WRONG_SECOND_INDEX, third_purchase);
        lastIndex = testPurchases.getPurchases().size()-1;
        Assert.assertEquals(testPurchases.getPurchases().indexOf(third_purchase), lastIndex);
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
        testPurchases.deleteByIndexes(-5,-7);
        Assert.assertTrue(errContent.toString().contains(Constants.WRONG_INDEX + "-5"));
        Assert.assertTrue(errContent.toString().contains(Constants.WRONG_INDEX + "-7"));
    }

    @Test
    public void testGetCost(){
        final Byn EXPECTED_COST = new Byn(4692);
        Assert.assertEquals(EXPECTED_COST, testPurchases.getTotalCost());
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