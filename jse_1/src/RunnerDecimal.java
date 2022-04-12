import by.gsu.epamlab.Constants;
import by.gsu.epamlab.DecimalMarkFactory;
import by.gsu.epamlab.HalfMarkFactory;
import by.gsu.epamlab.IResultDAO;
import by.gsu.epamlab.MarkFactory;
import by.gsu.epamlab.ResultImplXml;
import by.gsu.epamlab.ResultsLoader;

public class RunnerDecimal {

	public static void main(String[] args) {
		MarkFactory markFactory = new DecimalMarkFactory();
		RunnerLogic.execute(Constants.INPUT_FILE_NAME_XML, markFactory);
	}

}
