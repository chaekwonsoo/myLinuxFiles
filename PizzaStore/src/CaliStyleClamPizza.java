
public class CaliStyleClamPizza extends Pizza {

	@Override
	void prepare() {
		System.out.println("prepare: CaliStyleClamPizza!");
	}

	@Override
	void bake() {
		System.out.println("bake: CaliStyleClamPizza!");
	}

	@Override
	void cut() {
		System.out.println("cut: CaliStyleClamPizza!");
	}

	@Override
	void box() {
		System.out.println("box: CaliStyleClamPizza!");
	}
	
}
