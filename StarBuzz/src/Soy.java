
public class Soy extends Condiment {

	Baverage baverage;
	
	public Soy(Baverage baverage) {
		this.baverage = baverage;
	}
	
	@Override
	public String getDescription() {
		return baverage.getDescription() + ", Soy";
	}

	@Override
	public double cost() {
		return baverage.cost() + 0.15;
	}
	
}
