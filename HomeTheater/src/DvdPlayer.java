
public class DvdPlayer {
	Amplifier amplifier;
	
	public DvdPlayer() {}
	
	public void on() {
		System.out.println("dvd player on!");
	}
	
	public void off() {
		System.out.println("dvd player off!");
	}
	
	public void eject() {
		System.out.println("dvd player eject!");
	}
	
	public void pause() {
		System.out.println("dvd player pause!");
	}
	
	public void play(String movie) {
		System.out.println("dvd player play: " + movie);
	}
	
	public void setSurroundSound() {
		System.out.println("dvd player set surround sound!");
	}
	
	public void setTwoChannelAudio() {
		System.out.println("dvd player set two channel audio!");
	}
	
	public void stop() {
		System.out.println("dvd player stop!");
	}
}
