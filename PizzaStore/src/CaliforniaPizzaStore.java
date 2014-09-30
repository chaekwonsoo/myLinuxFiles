
public class CaliforniaPizzaStore extends PizzaStore {
	@Override
	Pizza createPizza(String item) {
		if(item.equals("cheese")) {
			return new CaliStyleCheesePizza();
		} else if(item.equals("veggie")) {
			return new CaliStyleVeggiePizza();
		} else if(item.equals("clam")) {
			return new CaliStyleClamPizza();
		} else if(item.equals("pepperoni")) {
			return new CaliStylePepperoniPizza();
		} else
			return null;
	}

}
