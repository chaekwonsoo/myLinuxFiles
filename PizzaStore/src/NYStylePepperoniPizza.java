
public class NYStylePepperoniPizza extends Pizza {

	@Override
	void prepare() {
		System.out.println("prepare: NYStylePepperoniPizza!");
	}

	@Override
	void bake() {
		System.out.println("bake: NYStylePepperoniPizza!");
	}

	@Override
	void cut() {
		System.out.println("cut: NYStylePepperoniPizza!");
	}

	@Override
	void box() {
		System.out.println("box: NYStylePepperoniPizza!");
	}
	
}
