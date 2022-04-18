package by.epam.lab;

import java.io.IOException;
import java.util.*;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import by.epam.lab.exceptions.SourceException;

import static by.epam.lab.Constants.*;

public class ResultImplXml implements ResultDao {
	
	Iterator<Result> iterator = null;
	
	public ResultImplXml() {
		
	}
	
	public ResultImplXml(String inputFileName) throws SourceException {
		XMLReader reader;
		try {
			reader = XMLReaderFactory.createXMLReader();
			ResultHandler handler = new ResultHandler();
	        reader.setContentHandler(handler);
			reader.parse(inputFileName);
			iterator = handler.getResults().iterator();
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
