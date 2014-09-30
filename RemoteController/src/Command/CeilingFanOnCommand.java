package Command;

import Appliances.CeilingFan;

public class CeilingFanOnCommand implements Command {
	private CeilingFan ceilingFan;
	private int prevSpeed;
	
	public CeilingFanOnCommand(CeilingFan ceilingFan) {
		this.ceilingFan = ceilingFan;
	}
	
	@Override
	public void execute() {
		//ceilingFan.on();
	}

	@Override
	public void undo() {
		ceilingFan.off();
	}
}
