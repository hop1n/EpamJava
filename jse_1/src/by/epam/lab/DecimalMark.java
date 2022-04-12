package by.gsu.epamlab;

public class DecimalMark extends Mark {
	
	public DecimalMark() {
		this(0);
	}
	
	public DecimalMark(int value) {
		super(value);
	}
	
	public DecimalMark(double value) {
		super((int) (value * 10));
	}
	
	public String toString() {
		int value = getValue();
		return value / 10 + "." + value % 10;
	}
	
}
