package athread.talk2;

import java.util.List;
import java.util.Vector;

public class Room {
	
	//선언부
	//외부에서 조작할 수 없도록 접근제한자로 private을 붙였음.
	private List<PotatoServerThread> 	userList = new Vector<>();	//★★★★★
	private List<String> 				nameList = new Vector<>();	//★★★★★
	private String 						title =  null;	//단톡방의 이름(제목)을 붙이기 위한 선언
	private String 						state = null;
	
	//Getter-Setter메소드
	public List<PotatoServerThread> getUserList() {
		return userList;
	}
	public void setUserList(List<PotatoServerThread> userList) {
		this.userList = userList;
	}
	public List<String> getNameList() {
		return nameList;
	}
	public void setNameList(List<String> nameList) {
		this.nameList = nameList;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
