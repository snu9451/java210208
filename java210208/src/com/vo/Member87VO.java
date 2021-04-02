package com.vo;

public class Member87VO {
	private String mem_id	= null;		//아이디 담기
	private String mem_pw	= null;		//비번 담기
	private boolean gender	= false;	//성별 담기, boolean의 default는 false
	/*
	 * 생성자는 반환타입(void, int ...)이 없다.
	 * 생성자의 이름은 클래스의 이름과 <동일>해야 한다.
	 * 역할: 전역변수의 초기화를 담당한다.
	 * static 블럭에서 하는 생성자에게 맡겨도 괜찮아~
	 * 만일 소켓통신을 통해서 서버사이드와 클라이트 사이트를 구현(구축)
	 * 
	 * **메소드 오버로딩**
	 * 전제조건 - 모두 같은 이름이다. 다른 이름을 사용한다면 메소드 오버로딩에 해당되지 않는다.
	 * 접근제한자의 유무가 영향을 미치지 않는다.
	 * 리턴타입의 유무에 영향을 받지 않는다. - 생성자는 리턴타입이 없으므로 다름.
	 * 예외를 주든 주지 않든 영향이 없다.
	 * void go() throws Exception, SQLException{}
	 * 
	 * "지역변수는 반드시 초기화를 해야하고 전역변수는 초기화를 생략할 수 있다."라는 말의 의미를 여기서 확인할 수 있다.
	 * 전역변수는 생성자를 이용해 나중에 초기화할 수 있다. 즉, 선언 시 초기화는 생략할 수 있다.
	 * 
	 */
	public Member87VO() {}				//디폴트 생성자
	public Member87VO(String mem_id) {	//파라미터가 있는 생성자
		this.mem_id = mem_id;
	}
	public Member87VO(String mem_id, String me_pw) { //파라미터가 있는 생성자
		this.mem_id = mem_id;
		this.mem_pw = mem_pw;
	}
	public Member87VO(String mem_id, String me_pw, boolean gender) { //파라미터가 있는 생성자
		this.mem_id = mem_id;
		this.mem_pw = mem_pw;
		this.gender = gender;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_pw() {
		return mem_pw;
	}
	public void setMem_pw(String mem_pw) {
		this.mem_pw = mem_pw;
	}

	public boolean isGender() {	//boolean은 is로 쓴다.
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
}
