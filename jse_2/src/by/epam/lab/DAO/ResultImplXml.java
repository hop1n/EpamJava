package by.epam.lab.DAO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import by.epam.lab.beans.Result;
import by.epam.lab.services.ResultHandler;
import by.epam.lab.services.ResultKind;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import by.epam.lab.exceptions.SourceException;

import static by.epam.lab.services.Constants.*;

public class ResultImplXml implements ResultDao {

    private Iterator<Result> iterator;

    public ResultImplXml(String inputFileName, ResultKind resultType) throws SourceException {
        XMLReader reader;
        try {
            reader = XMLReaderFactory.createXMLReader();
            ResultHandler handler = new ResultHandler(resultType);
            reader.setContentHandler(handler);
            reader.parse(inputFileName);
            iterator = handler.getResults().iterator();
        } catch (FileNotFoundException e) {
            throw new SourceException(FILE_NOT_FOUND);
        } catch (IOException e) {
            throw new SourceException(e.getMessage());
        } catch (SAXException e) {
            throw new SourceException(SAX_EXCEPTION);
        }
    }

    public Result nextResult() {
        return iterator.next();
    }

    public boolean hasResult() {
        return iterator.hasNext();
    }

    public void close() {
        iterator = null;
    }
}
