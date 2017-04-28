package cn.aplus.exception;

import cn.aplus.resp.BaseRespObj;

public class BaseException extends RuntimeException {

	private static final long serialVersionUID = 4458561523684817241L;
	private BaseRespObj<Object> resp;
	public BaseRespObj<Object> getResp() {
		return resp;
	}
	public void setResp(BaseRespObj<Object> resp) {
		this.resp = resp;
	}
	

}
