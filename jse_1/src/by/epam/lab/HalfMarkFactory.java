package by.gsu.epamlab;

public class HalfMarkFactory extends MarkFactory {
	
	public Mark getMarkFromFactory(int mark){
		return new HalfMark(mark);
	}
	
	public Mark getMarkFromFactory(String mark) {
		return new HalfMark(Double.parseDouble(mark));
	}
	
	public String getStringMark(double mark) {
		HalfMark hm = new HalfMark(mark);
		return hm.toString();
	}
	
}
