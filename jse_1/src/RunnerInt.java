import by.gsu.epamlab.*;

public class RunnerInt {

	public static void main(String[] args) {
		MarkFactory markFactory = new MarkFactory();
		RunnerLogic.execute(Constants.INPUT_FILE_NAME_CSV, markFactory);
	}
	
}
