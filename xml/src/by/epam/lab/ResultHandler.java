package by.epam.lab;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import static by.epam.lab.Constants.*;

import java.util.ArrayList;
import java.util.List;

public class ResultHandler extends DefaultHandler {
    private enum ResultEnum {
        RESULTS, STUDENT, LOGIN, TESTS, TEST;
    }
    private final List<Result> results = new ArrayList<>();
    private ResultEnum currentEnum;
    private String login;

    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        currentEnum = ResultEnum.valueOf(localName.toUpperCase());
        if (currentEnum == ResultEnum.TEST) {
            results.add(new Result(login, attributes.getValue(NAME_INDEX),
                        attributes.getValue(DATE_INDEX), attributes.getValue(MARK_INDEX)));
        }
    }

    public void printResults(){
        for (Result res: results){
            System.out.println(res);
        }
    }

    public void characters(char[] ch, int start, int length){
        String str = new String(ch, start, length).trim();
        if (currentEnum == ResultEnum.LOGIN) {
            if (!str.isEmpty()){
                login = str;
            }
        }
    }
}

