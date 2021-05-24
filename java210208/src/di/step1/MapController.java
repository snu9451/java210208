package di.step1;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class MapController extends AbstractController {
	
	Map<String, String> mapBean = null;
	
	public void setMapBean(Map<String, String> mapBean) {
		this.mapBean = mapBean;
	}
	
	public ModelAndView handleRequestInternal(HttpServletRequest req, HttpServletResponse res) {
		System.out.println(mapBean.get("prop1"));
		ModelAndView mab = new ModelAndView();
		return mab;
	}
}
