package cn.aplus.exception;

import cn.aplus.resp.BaseRespObj;
import cn.aplus.resp.RespCode;

public class ParamFormatException extends BaseException {

	private static final long serialVersionUID = -9073615987590737721L;

	public ParamFormatException(BaseRespObj<Object> resp) {
		if (resp.getRescode() == null)
			resp.setRespcode(RespCode.PARAM_FORMAT_ERROR);
		resp.setMessage(RespCode.PARAM_FORMAT_ERROR.getMessage());
		this.setResp(resp);
	}

	public ParamFormatException(BaseRespObj<Object> resp, String message) {
		if (resp.getRescode() == null)
			resp.setRespcode(RespCode.PARAM_FORMAT_ERROR);
		resp.setMessage(message);
		this.setResp(resp);
	}

}
