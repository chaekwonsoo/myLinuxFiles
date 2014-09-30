
public class NYStyleClamPizza extends Pizza {

	@Override
	void prepare() {
		System.out.println("prepare: NYStyleClamPizza!");
	}

	@Override
	void bake() {
		System.out.println("bake: NYStyleClamPizza!");
	}

	@Override
	void cut() {
		System.out.println("cut: NYStyleClamPizza!");
	}

	@Override
	void box() {
		System.out.println("box: NYStyleClamPizza!");
	}
	
}
