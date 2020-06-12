package com.testfile.Control;



import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import Http.Common.HttpParamType;
import Http.Header.HttpHeader;

@Controller
public class TestFileCreateControl {

	@RequestMapping(value="/testcreate",produces = "application/json;charset=UTF-8")
	public String test() {
		return "testcreate";
	}
	
	@RequestMapping(value="/GetSendDataType",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String GetSendDataType() {
	   JSONObject data=new JSONObject();
	   JSONArray dataarr=new JSONArray();
	   JSONObject form=new JSONObject();
	   JSONObject json=new JSONObject();

	   form.put("type", "application/x-www-form-urlencoded");
	   json.put("type", HttpParamType.application_json);
	   dataarr.put(form);
	   dataarr.put(json);
	   data.put("data", dataarr);
		return data.toString();
		
		
	}
}
