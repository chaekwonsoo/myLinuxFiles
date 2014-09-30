
public class SteamMilk extends Condiment {

	Baverage baverage;
	
	public SteamMilk(Baverage baverage) {
		this.baverage = baverage;
	}
	
	@Override
	public String getDescription() {
		return baverage.getDescription() + ", SteamMilk";
	}

	@Override
	public double cost() {
		return baverage.cost() + 0.1;
	}

}
