package cn.aplus.exception;

import cn.aplus.resp.BaseRespObj;
import cn.aplus.resp.RespCode;

public class ParamException extends BaseException {

	private static final long serialVersionUID = 4458561523684817241L;

	public ParamException(BaseRespObj<Object> resp) {
		if (resp.getRescode() == null)
			resp.setRespcode(RespCode.INVALID_FIELD);
		resp.setMessage(RespCode.INVALID_FIELD.getMessage());
		this.setResp(resp);
	}

	public ParamException(BaseRespObj<Object> resp, String message) {
		if (resp.getRescode() == null)
			resp.setRespcode(RespCode.INVALID_FIELD);
		resp.setMessage(message);
		this.setResp(resp);
	}

}
