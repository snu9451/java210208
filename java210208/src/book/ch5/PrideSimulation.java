package book.ch5;

public class PrideSimulation {

	public static void main(String[] args) {
//		Pride myCar		= new Pride();	//Pride()(디폴트 생성자)가 private으로 지정되어 있어서 에러남
//		Pride herCar	= new Pride();	//복제 불가. 유일무이.
//		Pride himCar	= new Pride();
		///////////////////////////////////////////////////////////////////////////////
		Pride herCar	= new Pride(10);
		Pride himCar	= new Pride(100, 4);
		///////////////////////////////////////////////////////////////////////////////
//		himCar.wheelNum = 4;
//		herCar.wheelNum = 14;	//이 두 코드에 의해, wheelNum은 14가 된다. <하나>이니까!!
		//himCar와 herCar로 나누는 의미가 없다!
		Pride.wheelNum	= 5;
		///////////////////////////////////////////////////////////////////////////////
		himCar.speed	= 10;	//각각 speed가 10, 20으로 저장된다. 각각 관리!!
		herCar.speed	= 50;
		///////////////////////////////////////////////////////////////////////////////
		
	}

}
