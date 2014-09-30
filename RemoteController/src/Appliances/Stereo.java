package Appliances;

public class Stereo {
	
	private int volume;
	private String place;
	
	public Stereo(String place) {
		this.place = place;
	}
	
	public void on() {
		System.out.println("Stereo on!");
	}
	
	public void setCD() {
		System.out.println("set CD!");
	}
	
	public void setVolume(int volume) {
		System.out.println("set volume!");
		this.volume = volume;
	}
	
	public void off() {
		System.out.println("Stereo off!");
	}
	
}
