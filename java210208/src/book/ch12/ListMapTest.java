package book.ch12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class ListMapTest {
	
	public static void main(String[] args) {
		
		List<String> li = new ArrayList<>();
		li.add(0, "사과");
		li.add("포도");
		li.add("키위");
		for(String s : li) {
			System.out.println(s);
		}
		
		System.out.println("===============================");
		
		Map<String, Object> map = new Hashtable<>();
		System.out.println(map);
		map.put("one", "사과");
		map.put("two", "포도");
		map.put("three", "키위");
		Object keys[] = null;
		keys = map.keySet().toArray();
		Object values[] = null;
		values = map.values().toArray();
//		keys[0]에는 "one"이, keys[1]에는 "two"가, keys[2]에는 "three"가 있겠다.
		for(int i=0; i<keys.length; i++) {
			String key = keys[i].toString();
			System.out.println(key);
		}
		for(int i=0; i<values.length; i++) {
			String value = values[i].toString();
			System.out.println(value);
		}
		
		System.out.println(map);
		
//		System.out.println(keys.length == values.length);	//true ★★★★★항상 같은가?
		
		
		
	}

}
