import RemoteControl.SimpleRemoteControl;
import Appliances.Light;
import Appliances.GarageDoor;
import Command.LightOnCommand;
import Command.GarageDoorOpenCommand;

public class RemoteControlTest {		// Client in the Command pattern.
	/*
	public static void main(String[] args) {
		SimpleRemoteControl remote = new SimpleRemoteControl();		// invoker
		Light light = new Light();										// receiver1
		GarageDoor garageDoor = new GarageDoor();						// receiver2
		
		LightOnCommand lighton = new LightOnCommand(light);			// command1
		GarageDoorOpenCommand garageDoorOpen = new GarageDoorOpenCommand(garageDoor); // command2
		
		remote.setCommand(lighton);
		remote.buttonWasPressed();
		remote.setCommand(garageDoorOpen);
		remote.buttonWasPressed();
	}
	*/
}
