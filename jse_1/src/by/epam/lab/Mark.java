package by.gsu.epamlab;

public class Mark implements Comparable<Mark> {
	private final int value;
	
	public Mark() {
		this(0);
	}
	
	public Mark(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public String toString() {
		return String.valueOf(value);
	}
	
	public int compareTo(Mark mark) {
		return value - mark.value;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mark other = (Mark) obj;
		if (value != other.value)
			return false;
		return true;
	}
	
}
