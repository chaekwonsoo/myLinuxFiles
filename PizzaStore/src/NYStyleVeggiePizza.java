
public class NYStyleVeggiePizza extends Pizza {

	@Override
	void prepare() {
		System.out.println("prepare: NYStyleVegiePizza!");
	}

	@Override
	void bake() {
		System.out.println("bake: NYStyleVegiePizza!");
	}

	@Override
	void cut() {
		System.out.println("cut: NYStyleVegiePizza!");
	}

	@Override
	void box() {
		System.out.println("box: NYStyleVegiePizza!");
	}
	
}
