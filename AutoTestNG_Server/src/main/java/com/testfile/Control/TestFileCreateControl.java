package com.testfile.Control;



import java.util.LinkedList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.testfile.DataPool.DataPoolConnector;
import com.testfile.DataPool.DataPoolXmlUtils;
import com.testfile.DataPool.DataTableInfo;
import com.testfile.DataPool.Create.DataCreatePoolTable;
import com.testfile.DataPool.DataOperation.DataInsert;

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
	
	@Autowired
	DataPoolConnector dpc;
	
	@Autowired
	DataPoolXmlUtils xml;
	
	@Autowired
	DataInsert datainsert;
	
	@RequestMapping(value="/datapool",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String datapool() {
		
		datainsert.DataInsert(new LinkedList(), "ImgInfo");
		return "success";
	}
}
