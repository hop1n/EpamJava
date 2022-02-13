import by.epam.lab.*;
import by.epam.lab.comparators.PurchaseComparator;
import by.epam.lab.exceptions.CsvLineException;
import by.epam.lab.exceptions.InvalidNameException;
import by.epam.lab.exceptions.InvalidNumberOfArgumentsException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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

        testPurchases = new PurchasesList(IN_PATH, COMPARATOR);
    }

    @After
    public void restoreStreams() {
        System.setErr(originalErr);
    }

    @Test
    public void testPurchasesListConstructor() throws InvalidNameException {
        final int EXPECTED_SIZE_OF_PURCHASES = 8;
        String EXPECTED_OUT = "candy;0;2 non positive value 0\r\n" +
                "candy;-100;-2 non positive value -100\r\n" +
                "candy;100;2;0 non positive value 0\r\n" +
                "candy wrong number of arguments\r\n" +
                ";100;2 Invalid name\r\n" +
                "beer;;1 wrong format of variable number, price or discount\r\n" +
                "candy;100;2;500 wrong discount\r\n" +
                "candy;100;2;100 wrong discount\r\n" +
                "water;15;4;0.1;cold wrong number of arguments\r\n" +
                "water;70;5;-1 non positive value -1\r\n" +
                ";; wrong number of arguments\r\n" +
                "water;ok;4 wrong format of variable number, price or discount\r\n" +
                "water;70;4;0.5 wrong format of variable number, price or discount\r\n" +
                "water;70.5;1 wrong format of variable number, price or discount\r\n";
        Assert.assertEquals(EXPECTED_OUT, errContent.toString());
        Assert.assertEquals(testPurchases.getClonedPurchases().size(), EXPECTED_SIZE_OF_PURCHASES);
    }

    @Test
    public void testInsertByIndex() throws InvalidNameException, InvalidNumberOfArgumentsException {
        final int RIGHT_INDEX = 2;
        final int WRONG_INDEX = -1;
        final int WRONG_SECOND_INDEX = 50;
        final int START_INDEX = 0;
        int lastIndex;
        String[] firstPurchaseData = {"water", "120", "10", "20"};
        String[] secondPurchaseData = {"bread", "100", "20", "10"};
        String[] thirdPurchaseData = {"apple", "50", "5", "10"};
        Purchase purchase = new PriceDiscountPurchase(firstPurchaseData);
        Purchase second_purchase = new PriceDiscountPurchase(secondPurchaseData);
        Purchase third_purchase = new PriceDiscountPurchase(thirdPurchaseData);
        testPurchases.insertByIndex(RIGHT_INDEX, purchase);
        Assert.assertEquals(testPurchases.getClonedPurchases().indexOf(purchase), RIGHT_INDEX);
        testPurchases.insertByIndex(WRONG_INDEX, second_purchase);
        Assert.assertEquals(testPurchases.getClonedPurchases().indexOf(second_purchase), START_INDEX);
        testPurchases.insertByIndex(WRONG_SECOND_INDEX, third_purchase);
        lastIndex = testPurchases.getClonedPurchases().size() - 1;
        Assert.assertEquals(testPurchases.getClonedPurchases().indexOf(third_purchase), lastIndex);
    }

    @Test
    public void testDeleteByIndexes() throws InvalidNameException {
        Purchase[] requiredPurchases = {
                testPurchases.getClonedPurchases().get(testPurchases.getClonedPurchases().size()-1),
        };
        int result = testPurchases.deleteByIndexes(testPurchases.getClonedPurchases().size()-1, testPurchases.getClonedPurchases().size());
        Assert.assertEquals(1, result);
        Assert.assertFalse(testPurchases.getClonedPurchases().contains(requiredPurchases[0]));
    }

    @Test
    public void testGetTotalCost() {
        final Byn EXPECTED_COST = new Byn(4692);
        Assert.assertEquals(EXPECTED_COST, testPurchases.getTotalCost());
    }

    @Test
    public void testPurchasesSort() throws InvalidNameException {
        testPurchases2 = new PurchasesList(IN_PATH, COMPARATOR);
        List<Purchase> listToSort = testPurchases2.getClonedPurchases();
        Collections.sort(listToSort, COMPARATOR);
        testPurchases.purchasesSort();
        Assert.assertEquals(testPurchases.getClonedPurchases(), listToSort);
    }

    @Test
    public void testSearchAnElement() throws InvalidNameException, InvalidNumberOfArgumentsException {
        testPurchases2 = new PurchasesList(IN_PATH, COMPARATOR);
        String[] requiredPurchaseData = {"meat", "1100", "2", "80"};
        Purchase requiredPurchase = new PriceDiscountPurchase(requiredPurchaseData);
        testPurchases.purchasesSort();
        int index = Collections.binarySearch(testPurchases.getClonedPurchases(), requiredPurchase, COMPARATOR);
        int index2 = testPurchases2.searchAnElement(requiredPurchase);
        Assert.assertEquals(index, index2);
    }

    @Test
    public void getPurchaseFromFactoryTest() throws CsvLineException {
        final PriceDiscountPurchase expectedPriceDiscountPurchase = new PriceDiscountPurchase("cucumber", new Byn(90), 5, new Byn(10));
        final Purchase expectedPurchase = new Purchase("apple", new Byn(50), 10);
        final String csvLine1 = "apple;50;10";
        final String csvLine2 = "cucumber;90;5;10";
        Purchase actualPurchase = PurchaseFactory.getPurchaseFromFactory(csvLine1);
        Purchase actualPriceDiscountPurchase = PurchaseFactory.getPurchaseFromFactory(csvLine2);
        assertEquals(expectedPurchase, actualPurchase);
        assertEquals(expectedPriceDiscountPurchase, actualPriceDiscountPurchase);

    }
}