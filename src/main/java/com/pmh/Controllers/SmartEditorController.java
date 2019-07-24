package com.pmh.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SmartEditorController {

	@RequestMapping("/editor")
	public String editor() {
		return "template/smartedit/SmartEditor2Skin";
	}
}
