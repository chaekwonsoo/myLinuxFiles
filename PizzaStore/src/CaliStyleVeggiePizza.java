
public class CaliStyleVeggiePizza extends Pizza {

	@Override
	void prepare() {
		System.out.println("prepare: CaliStyleVeggiePizza!");
	}

	@Override
	void bake() {
		System.out.println("bake: CaliStyleVeggiePizza!");
	}

	@Override
	void cut() {
		System.out.println("cut: CaliStyleVeggiePizza!");
	}

	@Override
	void box() {
		System.out.println("box: CaliStyleVeggiePizza!");
	}
	
}
