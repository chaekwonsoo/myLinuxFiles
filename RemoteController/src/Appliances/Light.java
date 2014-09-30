package Appliances;

public class Light {
	
	private String place;
	
	public Light(String place) {
		this.place = place;
	}
	
	public void on() {
		System.out.println("light on!");
	}
	
	public void off() {
		System.out.println("light off!");
	}
}
