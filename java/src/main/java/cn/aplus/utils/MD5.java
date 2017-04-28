package cn.aplus.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author PanHoucheng
 * @time 2016年1月28日 下午2:17:19
 */
public class MD5 {
	public static String md5(String password) {
		if (password != null && password != null) {
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(password.getBytes());
				return new BigInteger(1, md.digest()).toString(16);
			}catch(NoSuchAlgorithmException e){
				e.printStackTrace();
			}
		}
		return null;
	}

	public static String md5(String username, String password) {
		if (password != null && password != null) {
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(username.getBytes());
				md.update(password.getBytes());
				return new BigInteger(1, md.digest()).toString(16);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
