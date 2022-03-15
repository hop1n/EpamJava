import by.epam.lab.Result;
import by.epam.lab.ResultHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import static by.epam.lab.Constants.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        List<Result> list = new ArrayList<>();
        try {
            ResultHandler handler = new ResultHandler();
            XMLReader reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
            reader.parse(PATH);
            handler.printResults();
        } catch (IOException | SAXException e) {
            System.out.println(e);
        }

    }
}
