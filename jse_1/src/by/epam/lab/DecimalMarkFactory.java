package by.gsu.epamlab;

public class DecimalMarkFactory extends MarkFactory {
	
	public Mark getMarkFromFactory(int mark){
		return new DecimalMark(mark);
	}
	
	public Mark getMarkFromFactory(String mark){
		return new DecimalMark(Integer.parseInt(mark));
	}
	
	public String getStringMark(double mark) {
		return String.format("%.2f", (mark / 10));
	}
	
	public IResultDAO getImpl(String inputFile) {
		return new ResultImplXml(inputFile);
	}
	
}
