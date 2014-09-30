
public class ChicagoStylePepperoniPizza extends Pizza {

	@Override
	void prepare() {
		System.out.println("prepare: ChicagoStylePepperoniPizza!");
	}

	@Override
	void bake() {
		System.out.println("bake: ChicagoStylePepperoniPizza!");
	}

	@Override
	void cut() {
		System.out.println("cut: ChicagoStylePepperoniPizza!");
	}

	@Override
	void box() {
		System.out.println("box: ChicagoStylePepperoniPizza!");
	}
	
}
