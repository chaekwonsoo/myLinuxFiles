
public abstract class CafeineBaverage {
	
	final void prepareRecipe() {		// 'final': can never be overridden!
		boilWater();
		brew();
		pourInCup();
		addCondiments();
	}
	
	void boilWater() {
		System.out.println("물 끓이는 중...");
	}
	
	void pourInCup() {
		System.out.println("컵에 따르는 중...");
	}
	
	abstract void brew();
	abstract void addCondiments();
}
