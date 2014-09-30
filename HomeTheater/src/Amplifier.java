
public class Amplifier {
	Tuner tuner;
	DvdPlayer dvdPlayer;
	CdPlayer cdPlayer;
	
	public Amplifier() {}
	
	public void on() {
		System.out.println("amplifier on!");
	}
	
	public void off() {
		System.out.println("amplifier off!");
	}
	
	public void setCd(CdPlayer cd) {
		System.out.println("amplifier set cd: ");
	}
	
	public void setDvd(DvdPlayer dvd) {
		System.out.println("amplifier set dvd: ");
	}
	
	public void setStereoSound() {
		System.out.println("amplifier set stereo sound!");
	}
	
	public void setSurroundSound() {
		System.out.println("amplifier set surround sound!");
	}
	
	public void setTuner() {
		System.out.println("amplifier set tuner!");
	}
	
	public void setVolume(int volume) {
		System.out.println("amplifier set volume: " + volume);
	}
}
