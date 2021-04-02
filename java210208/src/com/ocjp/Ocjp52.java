package com.ocjp;

//enum Dogs {collie, harrier, shepherd};

public class Ocjp52 {
	
	public static void main(String [] args) {
		String myDog = "collie";
		switch (myDog) {						//myDog이라는 object타입이 왔음
			case "collie":						//case 다음에 reference타입이 왔음(합법이구나 - 그럼 String도 되겠다)
				System.out.println("collie");
			case "shepherd":
				System.out.println("shepherd");
			case "harrier":
				System.out.println("harrier");
		}///////////////end of switch
	}///////////////////end of main
}///////////////////////end of Ocjp52
