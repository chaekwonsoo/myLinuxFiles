
public class ChicagoStyleVeggiePizza extends Pizza {

	@Override
	void prepare() {
		System.out.println("prepare: ChicagoStyleVeggiePizza!");
	}

	@Override
	void bake() {
		System.out.println("bake: ChicagoStyleVeggiePizza!");
	}

	@Override
	void cut() {
		System.out.println("cut: ChicagoStyleVeggiePizza!");
	}

	@Override
	void box() {
		System.out.println("box: ChicagoStyleVeggiePizza!");
	}
	
}
