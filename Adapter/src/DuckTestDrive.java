
public class DuckTestDrive {
	public static void main(String[] args) {
		MallardDuck duck = new MallardDuck();
		
		WildTurkey turkey = new WildTurkey();
		Duck turkeyAdapter = new TurkeyAdapter(turkey);
		
		System.out.println("The turkey says...");
		turkey.gobble();
		turkey.fly();
		
		System.out.println(System.getProperty("line.separator") + "The duck says...");
		testDuck(duck);
		
		System.out.println(System.getProperty("line.separator") + "The turkeyAdapter says...");
		testDuck(turkeyAdapter);
	}
	
	static void testDuck(Duck duck) {	// Here this method cannot distinguish between duck and turkey!
		duck.quack();
		duck.fly();
	}
}
