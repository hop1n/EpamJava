package by.gsu.epamlab;

public class HalfMark extends Mark {
	
	public HalfMark() {
		super(0);
	}
	
	public HalfMark(int value) {
		super(value);
	}
	
	public HalfMark(double value) {
		super((int)(value * 2));
	}
	
	public String toString() {
		int mark = getValue();
		String result = String.valueOf(mark / 2); 
		if (mark % 2 != 0) { 
			result = result + ".5"; 
		} 
		return result;
	}
	
}
