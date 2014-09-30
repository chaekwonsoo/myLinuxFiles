
public class CaliStyleCheesePizza extends Pizza {

	@Override
	void prepare() {
		System.out.println("prepare: CaliStyleCheesePizza!");
	}

	@Override
	void bake() {
		System.out.println("bake: CaliStyleCheesePizza!");
	}

	@Override
	void cut() {
		System.out.println("cut: CaliStyleCheesePizza!");
	}

	@Override
	void box() {
		System.out.println("box: CaliStyleCheesePizza!");
	}
	
}
