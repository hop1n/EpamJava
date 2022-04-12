package by.gsu.epamlab;

public class MarkFactory {
	public Mark getMarkFromFactory(int mark) {
		return new Mark(mark);
	}
	
	public Mark getMarkFromFactory(String mark) {
		return new Mark(Integer.parseInt(mark));
	}
	
	public String getStringMark(double mark) {
		return String.valueOf(mark);
	}
	
	public IResultDAO getImpl(String inputFile) {
		return new ResultImplCsv(inputFile, this);
	}
}
