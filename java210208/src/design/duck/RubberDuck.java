package design.duck;

public class RubberDuck extends Duck {
	public RubberDuck() {
		flyBehavior = new FlyNoWay();
		quackBehavior = new Squeack();
	}
	@Override
	public void display() {
		System.out.println("나는 고무 오리입니다.");
	}

}
