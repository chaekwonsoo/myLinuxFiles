
public class Mocha extends Condiment {

	// composition(구성) here (wrap(decorate) this baverage.)
	Baverage baverage;
	
	public Mocha(Baverage baverage) {
		this.baverage = baverage;
	}
	
	@Override
	public String getDescription() {
		return baverage.getDescription() + ", Mocha";
	}

	@Override
	public double cost() {
		return baverage.cost() + 0.2;
	}
	
}
