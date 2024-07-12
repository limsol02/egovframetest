package egovframework.example.sample.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.example.sample.service.MainService;

@Controller
public class MainController {
	@Resource(name="mainService")
	private MainService service;
	
	@RequestMapping(value = "chartData.do", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> chartData(Model d) throws Exception {
		
		Map<String, Object> result = new HashMap<>();
		result.put("allData", service.allBoardCount());
		result.put("boardWithFile", service.boardWithFile());
		return result;
	}
}
