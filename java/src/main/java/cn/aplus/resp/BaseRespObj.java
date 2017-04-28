package cn.aplus.resp;

import java.io.Serializable;


public class BaseRespObj<T> implements Serializable{
	
	/**
	 * serialVersionUID:serialVersionUID.
	 * @since JDK 1.8
	 */
	private static final long serialVersionUID = 1L;

	private RespCode respcode;

	private String message;
	
	private int rcode;
	
	private T data;

	public BaseRespObj() {
		this(RespCode.PASS_OK);
	}
	
	public BaseRespObj(RespCode respCode){
		this.respcode = respCode;
		this.rcode = respCode.getCode();
		this.message = respCode.getMessage();
	}

	public RespCode getRescode() {
		return respcode;
	}

	public void setRespcode(RespCode rescode) {
		this.respcode = rescode;
		this.rcode = rescode.getCode();
		this.message = rescode.getMessage();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getRcode() {
		return rcode;
	}

	public void setRcode(int rcode) {
		this.rcode = rcode;
	}

	@Override
	public String toString() {
		return "BaseRespObj [respcode=" + respcode + ", message=" + message
				+ ", rcode=" + rcode + ", data=" + getData() + "]";
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
