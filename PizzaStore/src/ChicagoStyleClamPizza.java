
public class ChicagoStyleClamPizza extends Pizza {

	@Override
	void prepare() {
		System.out.println("prepare: ChicagoStyleClamPizza!");
	}

	@Override
	void bake() {
		System.out.println("bake: ChicagoStyleClamPizza!");
	}

	@Override
	void cut() {
		System.out.println("cut: ChicagoStyleClamPizza!");
	}

	@Override
	void box() {
		System.out.println("box: ChicagoStyleClamPizza!");
	}
	
}
