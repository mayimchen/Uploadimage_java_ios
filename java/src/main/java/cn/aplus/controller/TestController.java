package cn.aplus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.aplus.exception.ParamException;
import cn.aplus.resp.BaseRespObj;
import cn.aplus.resp.RespCode;
import cn.aplus.resp.SimpleResp;
import cn.aplus.text.UserText;
import cn.aplus.utils.StringUtils;

@Controller
@RequestMapping("/test")
public class TestController extends BaseController {

	@RequestMapping(value = "/test", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public BaseRespObj<SimpleResp> test(
			@RequestParam(value = "f", required = false) Float email) {
		if (StringUtils.isEmpty(email)) {
			throw new ParamException(new BaseRespObj<Object>(
					RespCode.INVALID_FIELD), UserText.USER_EMAIL_IS_NULL);
		}
		BaseRespObj<SimpleResp> baseRespObj = new BaseRespObj<SimpleResp>();
		SimpleResp simpleResp = new SimpleResp();
		simpleResp.setResult(email.toString());
		baseRespObj.setData(simpleResp);
		return baseRespObj;
	}
}
