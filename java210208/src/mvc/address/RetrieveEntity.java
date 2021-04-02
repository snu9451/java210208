package mvc.address;

import java.util.List;

public class RetrieveEntity {

	//ㅇ★★★★★오버로딩에 리턴타입은 영향이 없다!★★★★★
	//MN; 서로 다른 return 타입을 if문으로 처리할 수 없다. return 타입은 하나여야 하므로.
	
	public AddressVO select(AddressVO vo) {	//무조건 한개의 로우만 가능함.
		System.out.println("RetrieveEntity select(vo) 호출 성공");
		return null;
	}
	
//	public AddressVO[] select() {	//이렇게 파라미터가 없으면 [조건 검색]을 할 수 없다는 단점이 있다.
//		return null;
//	}
	
	public AddressVO[] select() {	//n개의 로우 가능함.
		System.out.println("RetrieveEntity select() 호출 성공");
		return null;
	}
	
	public List<AddressVO> selectList(AddressVO vo) {	//n개의 로우 가능함.
		System.out.println("RetrieveEntity selectList() 호출 성공");
		return null;
	}
	
}
