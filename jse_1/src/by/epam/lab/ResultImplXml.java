package by.gsu.epamlab;

import java.io.IOException;
import java.util.*;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import by.gsu.epamlab.exceptions.SourceException;

public class ResultImplXml implements IResultDAO {
	
	Iterator<Result> iterator = null;
	
	public ResultImplXml() {
		
	}
	
	public ResultImplXml(String inputFileName) {
		XMLReader reader;
		try {
			reader = XMLReaderFactory.createXMLReader();
			ResultHandler handler = new ResultHandler();
	        reader.setContentHandler(handler);
			reader.parse(inputFileName);
			iterator = handler.getResults().iterator();
		} catch (SAXException e) {
           throw new SourceException(Constants.SAX_PARSER_ERROR);
        } catch (IOException e) {
            throw new SourceException(Constants.IO_THREAD_ERROR);
        }
	}

	public Result nextResult() {
		return iterator.next();
	}

	public boolean hasResult() {
		return iterator.hasNext();
	}

	public void closeReader() {
		iterator = null;
	}

}
