package Appliances;

public class GarageDoor {
	
	private String place;
	
	public GarageDoor(String place) {
		this.place = place;
	}
	
	public void up() {
		System.out.println("차고 문이 열립니다.");
	}
	public void down() {
		System.out.println("Garage Door is closed.");
	}
	public void stop() {
		System.out.println("Garage Door stops.");
	}
	public void lightOn() {
		System.out.println("Garage Door Light is on.");
	}
	public void lightOff() {
		System.out.println("Garage Door Light is off.");
	}
}
