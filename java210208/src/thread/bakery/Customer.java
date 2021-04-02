package thread.bakery;

import java.util.Vector;

public class Customer extends Thread {
	BakerStack		bs		= null;
	Vector<String>	cart	= new Vector<>();

	public Customer(BakerStack bs) {
		this.bs = bs;
	}

	@Override
	public void run() {
		String bread = null;
		bread = bs.pop();
		cart.add(bread);
		try {
			Thread.sleep((int) (Math.random() * 1000));
		} catch (InterruptedException ie) {
			System.out.println("누구신가요~?");
		}
	}
}
