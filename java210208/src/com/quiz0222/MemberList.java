package com.quiz0222;
import com.google.gson.Gson;
import com.vo.Member87VO;
public class MemberList {

	public static void main(String[] args) {
		Member87VO mvo = new Member87VO();
		mvo.setMem_id("apple");
		mvo.setMem_pw("123");
		mvo.setGender(true);
		mvo = new Member87VO("tomato","123",true);
		Gson gson = new Gson();
		String imsi = gson.toJson(mvo);
		System.out.println(imsi);
		
	}

}
