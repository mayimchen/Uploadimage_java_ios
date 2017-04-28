package cn.aplus.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.aplus.exception.ParamException;
import cn.aplus.resp.BaseRespObj;
import cn.aplus.resp.BooleanAndMessageResp;
import cn.aplus.resp.RespCode;
import cn.aplus.text.ProjectKeys;
import cn.aplus.text.UserText;
import cn.aplus.utils.CommonUtils;
import cn.aplus.utils.StringUtils;

@Controller
public class FileController extends BaseController {

	@RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
	@ResponseBody
	public BaseRespObj<BooleanAndMessageResp> addUploadImage(
			@RequestParam(value = "file", required = false) MultipartFile multipartFile,
			HttpServletRequest request) {
		System.out.println(multipartFile);
		if (StringUtils.isEmpty(multipartFile) || multipartFile.getSize() <= 0L) {
			throw new ParamException(new BaseRespObj<Object>(
					RespCode.INVALID_FIELD), UserText.FILE_PARAM_NULL);
		}
		if (multipartFile.getSize() > ProjectKeys.HEAD_IMAGE_MAX_FILE_SIZE) {
			throw new ParamException(new BaseRespObj<Object>(
					RespCode.USER_UPLOAD_IMAGE_SIZE_MAX), UserText.FILE_SIZE_MAX);
		}
		BaseRespObj<BooleanAndMessageResp> baseRespObj = new BaseRespObj<BooleanAndMessageResp>();
		BooleanAndMessageResp booleanAndMessageResp = new BooleanAndMessageResp();
		String path = request.getSession().getServletContext().getRealPath("/") + "upload";
		String oldName = multipartFile.getOriginalFilename().toLowerCase();
		if (!(oldName.endsWith(".jpg") || oldName.endsWith(".png"))) {
			booleanAndMessageResp.setFlag(false);
			booleanAndMessageResp.setMessage(UserText.FILE_FORMAT_NOT_SUPPORT);
			baseRespObj.setData(booleanAndMessageResp);
			return baseRespObj;
		}
		String endFileName = oldName.substring(oldName.lastIndexOf('.'));
		String fileName = CommonUtils.getUUIDRemoveLine() + endFileName;
		File targetFile = new File(path, fileName);
		//System.out.println(targetFile.getAbsolutePath());
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		try {
			multipartFile.transferTo(targetFile);
		} catch (Exception e) {
			booleanAndMessageResp.setFlag(false);
			booleanAndMessageResp.setMessage(UserText.FILE_SAVE_INNER_ERROR);
			baseRespObj.setData(booleanAndMessageResp);
			return baseRespObj;
		}
		//----------------------------------文件备份-----------------------//
//		String backupPath = "D://logs/upload";
//		String backupPath = "/Users/binhaizhu/Desktop/images";
//		File backupFile = new File(backupPath, fileName);
//		BufferedInputStream inBuff = null;
//		BufferedOutputStream outBuff = null;
//		try {
//			inBuff = new BufferedInputStream(new FileInputStream(targetFile));
//			outBuff = new BufferedOutputStream(new FileOutputStream(backupFile));
//			
//			byte[] b = new byte[1024 * 5];
//			int len;
//			while ((len = inBuff.read(b)) != (-1)) {
//				outBuff.write(b, 0, len);
//			}
//			
//			outBuff.flush();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				inBuff.close();
//				outBuff.close();
//			} catch (IOException e) {
//			}
//		}
		//----------------------------------文件备份end--------------------------//
		booleanAndMessageResp.setFlag(true);
//		booleanAndMessageResp.setMessage(request.getContextPath() + "/upload/"
//				+ fileName);
//		booleanAndMessageResp.setMessage("http://"+request.getServerName()+":"+request.getLocalPort()+request.getContextPath() + "/upload/"
//				+ fileName);
		booleanAndMessageResp.setMessage("http://"+request.getServerName()+request.getContextPath() + "/upload/"
				+ fileName);
		baseRespObj.setData(booleanAndMessageResp);
		return baseRespObj;
	}
}
