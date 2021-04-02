package design.duck;

public class DuckSimulation {

	public static void main(String[] args) {

		Duck myDuck = new WoodDuck();
		myDuck.display();
		myDuck.performFly();
		myDuck.performQuack();
		
		System.out.println("===========================");
		
		Duck herDuck = new WoodDuck();
		herDuck.display();
		herDuck.performFly();
		herDuck.performQuack();
		
		System.out.println("===========================");
		
		Duck himDuck = new MallardDuck();
		himDuck.display();
		himDuck.performFly();
		himDuck.performQuack();
	}

}
