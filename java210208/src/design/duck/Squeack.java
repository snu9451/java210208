package design.duck;

public class Squeack implements QuackBehavior {

	@Override
	public void quack() {
		System.out.println("삐이익~ 삑~~");
	}

}
