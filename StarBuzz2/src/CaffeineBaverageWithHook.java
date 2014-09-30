
public abstract class CaffeineBaverageWithHook {
	void prepareRecipe() {
		boilWater();
		blew();
		pourInCup();
		if(customerWantsCondiments()) {
			addCondiments();
		}
	}
	
	void boilWater() {
		System.out.println("물 끓이는 중!");
	}
	
	void pourInCup() {
		System.out.println("컵에 따르는 중!");
	}
	
	abstract void blew();
	abstract void addCondiments();
	
	// hook!
	boolean customerWantsCondiments() {
		return true;
	}
}
