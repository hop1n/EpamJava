package by.epam.lab;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import static by.epam.lab.Constants.*;

public class logicFactory {
    public static void getCsvResultsFromFactory(String csvPath) {
        ResultLoader.clearTables();
        String csvLine;
        try (Scanner sc = new Scanner(new FileReader(csvPath)); Connection cn = DBConnector.getConnection()) {
            while (sc.hasNextLine()) {
                csvLine = sc.nextLine();
                ResultLoader.loadResult(csvLine.split(DELIMITER));
            }
            ResultLoader.showStatInConsole();
        } catch (FileNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getXmlResultsFromFactory(String xmlPath) {
        ResultLoader.clearTables();
        try {
            ResultHandler handler = new ResultHandler();
            XMLReader reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
            reader.parse(xmlPath);
        } catch (IOException | SAXException e) {
            System.out.println(e);
        }
        ResultLoader.showStatInConsole();
    }
}
