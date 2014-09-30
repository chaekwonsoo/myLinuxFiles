package Appliances;

public class TV {
	
	private String place;
	
	public TV(String place) {
		this.place = place;
	}
	
	public void on() {
		System.out.println("TV on!");
	}
	
	public void off() {
		System.out.println("TV off!");
	}
}
