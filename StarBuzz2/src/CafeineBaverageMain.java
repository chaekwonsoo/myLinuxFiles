
public class CafeineBaverageMain {
	public static void main(String[] args) {
		TeaWithHook teaHook = new TeaWithHook();
		CoffeeWithHook coffeeHook = new CoffeeWithHook();
		
		System.out.println(System.getProperty("line.separator") + "차 준비중!");
		teaHook.prepareRecipe();
		
		System.out.println(System.getProperty("line.separator") + "커피 준비중!");
		coffeeHook.prepareRecipe();
	}
}
