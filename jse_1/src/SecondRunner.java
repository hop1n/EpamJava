import by.epam.lab.Result;
import by.epam.lab.Result2Task;
import by.epam.lab.ResultHandler;
import jdk.internal.org.objectweb.asm.Handle;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import static by.epam.lab.Constants.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class SecondRunner {
    public static void main(String[] args) {
        try {
            ResultHandler handler = new ResultHandler();
            XMLReader reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
            reader.parse(PATH);
            handler.task2();
        } catch (IOException | SAXException e) {
            System.out.println(e);
        }
    }
}

