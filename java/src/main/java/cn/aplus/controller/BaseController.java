package cn.aplus.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.alibaba.fastjson.JSONObject;

import cn.aplus.exception.BaseException;
import cn.aplus.exception.ParamException;
import cn.aplus.exception.ParamFormatException;
import cn.aplus.resp.BaseRespObj;
import cn.aplus.resp.RespCode;

public class BaseController {

	@ExceptionHandler({ParamFormatException.class,ParamException.class})
	public void handleException(BaseException ex,HttpServletResponse response) {
		// 设置状态码,注意这里不能设置成500，设成500JQuery不会出错误提示,并且不会有任何反应 
		response.setStatus(HttpStatus.OK.value());
		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache, must-revalidate");
		try {
			PrintWriter writer = response.getWriter();
			writer.write(JSONObject.toJSONString(ex.getResp()));
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}  
	
	@ExceptionHandler({Exception.class})
	public void handleException1(Exception ex,HttpServletResponse response) {
		// 设置状态码,注意这里不能设置成500，设成500JQuery不会出错误提示,并且不会有任何反应 
		response.setStatus(HttpStatus.OK.value());
		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache, must-revalidate");
		try {
			PrintWriter writer = response.getWriter();
			writer.write(JSONObject.toJSONString(new BaseRespObj<Object>(RespCode.EX_SERVER_INNER)));
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}  
}
