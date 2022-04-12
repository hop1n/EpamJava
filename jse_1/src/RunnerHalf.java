import by.gsu.epamlab.Constants;
import by.gsu.epamlab.*;

public class RunnerHalf {

	public static void main(String[] args) {
		MarkFactory markFactory = new HalfMarkFactory();
		RunnerLogic.execute(Constants.INPUT_FILE_NAME_CSV, markFactory);
	}

}
