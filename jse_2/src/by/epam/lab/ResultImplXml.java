package by.epam.lab;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import by.epam.lab.exceptions.SourceException;

import static by.epam.lab.Constants.*;

public class ResultImplXml implements ResultDao {

    Iterator<Result> iterator = null;

    public int getResultType() {
        return resultType;
    }

    private final int resultType;

    public ResultImplXml(String inputFileName, int resultType) throws SourceException {
        this.resultType = resultType;
        XMLReader reader;
        try {
            reader = XMLReaderFactory.createXMLReader();
            ResultHandler handler = new ResultHandler(resultType);
            reader.setContentHandler(handler);
            reader.parse(inputFileName);
            iterator = handler.getResults().iterator();
        } catch (FileNotFoundException e) {
            throw new SourceException(FILE_NOT_FOUND);
        } catch (SAXException e) {
            throw new SourceException(SAX_EXCEPTION);
        } catch (IOException e) {
            throw new SourceException(IO_EXCEPTION);
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
