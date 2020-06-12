package com.mock.URLDealService;

import org.springframework.stereotype.Component;

/**
 * Url工具
 * @author jinxh29224
 *
 */
@Component
public class UrlUtils{
	//斜杠
	private String slash="f41e90af75ef4173b92a2ab22a99a570";
	//问号
	private String question_mark="877a8280d4cf4b5e8e680c4b5083ec84";
	
	private String equal_mark="00fba38417a44a0eb67b9e1ea9959c19";
	public String UrlParserBefore(String Url){
		return Url.replace("/", slash).replace("?", question_mark).replace("=", equal_mark);
	
		
	}
    public String UrlParserAfter(String Url){
    	return Url.replace(slash,"/").replace(question_mark,"?").replace(equal_mark,"=");
	}
	
	
	
	
	public void GetAllUrl(){
		
		
	}
	
	
	/**
	 * url映射数据
	 * @return
	 */
    public String UrlMappingData(String url) {
		
		return null;
	}

    
  
}
