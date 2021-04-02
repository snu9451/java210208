package design.duck;

public class WoodDuck extends Duck {
	public WoodDuck() {
		flyBehavior = new FlyNoWay();	//flyBehavior는 부모클래스에 선언해 둔 변수임.
		quackBehavior = new MuteQuack();
		flyBehavior.fly();
	}
	@Override
	public void display() {
		System.out.println("나는 나무 오리입니다.");
	}

}
