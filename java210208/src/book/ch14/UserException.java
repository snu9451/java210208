package book.ch14;

public class UserException extends Exception {
	// encapsulation 을 위해 private으로 선언하고 getter, setter 사용하자.
	private int port = 7000;
	public UserException() {
		
	}
	public UserException(String msg) {
		super(msg);	//여기서 super는  Exception을 가리킴.
		//부모 클래스(Exception)에는 생성자 파라미터의 두번째 자리가  int인 생성자가 없다.
//		super(msg, port);
	}
	public UserException(String msg, int errorNum) {
		super(msg);
	}
}
