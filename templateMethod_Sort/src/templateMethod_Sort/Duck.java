package templateMethod_Sort;

public class Duck implements Comparable {
	String name;
	int weight;
	
	public Duck(String name, int weight) {
		this.name = name;
		this.weight = weight;
	}
	
	public String toString() {
		return name + ", 체중: " + weight;
	}

	@Override
	public int compareTo(Object obj) {
		Duck otherDuck = (Duck)obj;
		
		if(this.weight < otherDuck.weight) {
			return -1;
		} else if(this.weight == otherDuck.weight) {
			return 0;
		} else {
			return 1;
		}
	}
	
}
