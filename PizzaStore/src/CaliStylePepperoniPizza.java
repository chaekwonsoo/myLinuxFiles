
public class CaliStylePepperoniPizza extends Pizza {

	@Override
	void prepare() {
		System.out.println("prepare: CaliStylePepperoniPizza!");
	}

	@Override
	void bake() {
		System.out.println("bake: CaliStylePepperoniPizza!");
	}

	@Override
	void cut() {
		System.out.println("cut: CaliStylePepperoniPizza!");
	}

	@Override
	void box() {
		System.out.println("box: CaliStylePepperoniPizza!");
	}
	
}
