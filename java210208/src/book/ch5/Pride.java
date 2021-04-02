package book.ch5;
/*************************************************************************
 * <싱글톤 패턴의 정의>
 * 해당 클래스의 인스턴스가 하나만 만들어지고, 어디서든지 그 인스턴스에 접근할 수 있도록 하기 위한 패턴이다.
 * 방법1: 고전적인 싱글톤 패턴 구현법
 * 방법2: 성능이나 병목현상을 고려하여 구현하기
 * 
 *************************************************************************/
public class Pride {
	
	static int	wheelNum	= 0;
	int			speed		= 0;
	public static Pride pr	= null;
	private Pride() {}	//생성자의 encapsulation	//하나만 관리하기 위해서는 <null체크>가 필요
	public static Pride getInstance() {
		if(pr == null) {
			Pride pr = new Pride();
		}
		return pr;
	}
	public 	Pride(int speed) {
		this.speed = speed;
	}
	public 	Pride(int speed, int wheelNum) {
		this.speed 		= speed;
		this.wheelNum 	= wheelNum;
	}
	void speedUp() {	//Non-static method
		speed += 1;
	}
	static void stop() {
//		speed = 0;	//문법에러; static에서 non-static 변수를 사용할 수 없다?
	}
	static void change() {
		wheelNum = 2;
	}
}
