package by.epam.lab;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static by.epam.lab.Constants.*;

public class ResultHandler extends DefaultHandler {
    private enum ResultEnum {
        RESULTS, STUDENT, LOGIN, TESTS, TEST;
    }

    private ResultEnum currentEnum;
    private String login;
    private final List<Result> results = new ArrayList<>();

    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) {
        currentEnum = ResultEnum.valueOf(localName.toUpperCase());
        if (currentEnum == ResultEnum.TEST) {
            String[] parts = new String[]{login, attributes.getValue(NAME_INDEX),
                    attributes.getValue(DATE_INDEX), attributes.getValue(MARK_INDEX)};
            results.add(new Result(login, attributes.getValue(NAME_INDEX),
                    Date.valueOf(attributes.getValue(DATE_INDEX)), Integer.parseInt(attributes.getValue(MARK_INDEX))*10));
        }
    }

    public void characters(char[] ch, int start, int length) {
        String str = new String(ch, start, length).trim();
        if (currentEnum == ResultEnum.LOGIN) {
            if (!str.isEmpty()) {
                login = str;
            }
        }
    }

    public List<Result> getResults() {
        return results;
    }
}
