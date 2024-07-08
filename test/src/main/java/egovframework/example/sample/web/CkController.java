package egovframework.example.sample.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CkController {
	
	@RequestMapping(value="ckEditor.do", method = RequestMethod.GET)
	public String ckEditor() {
		return "test/ckeditor";
	}
	
}
