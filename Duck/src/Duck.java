
public abstract class Duck {
	
	// All the subclasses in the same package inherits these variables.
	QuackBehavior quackBehavior;
	FlyBehavior flyBehavior;
	
	public void performQuack() {
		quackBehavior.quack();
	}
	
	public void performFly() {
		flyBehavior.fly();
	}
	
	public void setFlyBehavior(FlyBehavior fb) {
		flyBehavior = fb;
	}
	
	public void setQuackBehavior(QuackBehavior qb) {
		quackBehavior = qb;
	}
	
	
	public void swim() {
		System.out.println("All ducks can swim!");
	}
	
	public abstract void display();
}
