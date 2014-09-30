package Appliances;

public class CeilingFan {
	
	public static final int HIGH = 3;
	public static final int MEDIUM = 2;
	public static final int LOW = 1;
	public static final int OFF = 0;
	
	private String place;
	private int speed;
	
	public CeilingFan(String place) {
		this.place = place;
		speed = OFF;
	}
	
	public void high() {
		System.out.println("fan high!");
		speed = HIGH;
	}
	
	public void medium() {
		System.out.println("fan medium!");
		speed = MEDIUM;
	}
	
	public void low() {
		System.out.println("fan low!");
		speed = LOW;
	}
	
	/*
	public void on() {
		System.out.println("Ceiling Fan on!");
	}
	*/
	
	public void off() {
		System.out.println("Ceiling Fan off!");
		speed = OFF;
	}
	
	public int getSpeed() {
		return speed;
	}
}
