package di.step1;

import java.util.Map;

public class InsaMap {
//	private List<String> insaBean = null; //encapsulation을 위해 private으로 제한 //private때문에 접근이 안돼서 빼기로함
	Map<String, String> mapBean = null;

	public void setMapBean(Map<String, String> mapBean) {
		this.mapBean = mapBean;
	}
}
