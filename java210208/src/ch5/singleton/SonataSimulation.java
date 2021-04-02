package ch5.singleton;

public class SonataSimulation {

	public static void main(String[] args) {
		Sonata yourCar	= Sonata.getInstance();
		Sonata myCar	= Sonata.getInstance();
		Sonata gnomCar	= Sonata.getInstance();
		System.out.println(yourCar);
		System.out.println(myCar);
		System.out.println(gnomCar);
	}

}
