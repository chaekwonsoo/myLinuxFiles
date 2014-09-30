
public class Whip extends Condiment {

	Baverage baverage;
	
	public Whip(Baverage baverage) {
		this.baverage = baverage;
	}
	
	@Override
	public String getDescription() {
		return baverage.getDescription() + ", Whip";
	}

	@Override
	public double cost() {
		return baverage.cost() + 0.1;
	}
	
}
