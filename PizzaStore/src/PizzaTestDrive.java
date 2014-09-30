
public class PizzaTestDrive {
	public static void main(String[] args) {
		PizzaStore nyStore = new NYPizzaStore();
		PizzaStore chicagoStore = new ChicagoPizzaStore();
		
		Pizza pizza = nyStore.orderPizza("cheese");
		System.out.println("Ethan ordered a " + pizza.getName() + System.getProperty("line.separator"));
		
		pizza = chicagoStore.orderPizza("cheese");
		System.out.println("Joel orderd a " + pizza.getName() + System.getProperty("line.separator"));
	}
}
