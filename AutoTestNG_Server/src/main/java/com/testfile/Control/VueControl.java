package com.testfile.Control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VueControl {
	@RequestMapping(value="/vue",produces = "application/json;charset=UTF-8")
	public String test() {
		return "vuetest";
	}
}
