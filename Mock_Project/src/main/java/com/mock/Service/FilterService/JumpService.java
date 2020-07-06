package com.mock.Service.FilterService;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponseWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mock.Utils.ControlUtils.UrlUtils;

/**
 * 跳转服务,来判断时重定向还是跳转
 * @author jinxh29224
 *
 */
@Service
public class JumpService {
	
	@Autowired
	UrlUtils UrlUtils;
	
    /**
     * 重定向
     * @param httpRequest
     * @param httpResponse
     */
	public void redict(HttpServletRequest httpRequest, HttpServletResponseWrapper httpResponse){
		
		
	}
	
	/**
	 * 跳转到本地url去获取mock数据
	 * @param httpRequest
	 * @param httpResponse
	 */
	public void forward(HttpServletRequest httpRequest, ServletRequest request, ServletResponse response) {
		 try {
			httpRequest.getRequestDispatcher("/mock/data?data="+UrlUtils.UrlParserBefore(httpRequest.getRequestURI())).forward(request,response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 放行
	 * @param httpRequest
	 * @param httpResponse
	 * @param chain
	 */
	public void release(ServletRequest httpRequest, ServletResponse httpResponse,FilterChain chain) {
		
		try {
			chain.doFilter(httpRequest, httpResponse);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}

}
