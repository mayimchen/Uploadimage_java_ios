package cn.aplus.text;
/**
 * @author PanHoucheng
 * @time 2016年1月31日 下午9:57:54
 */
public class ProjectKeys {
	//勿更改，密码加密及解密秘钥
	public final static String DES = "9qlbd8slfgjw5klnphc2";
	
	//Token过期时间，单位:月
	public final static Integer TOKEN_EXPIRE_TIME = 3;
	
	//VerifyCode过期时间，单位:分钟
	public final static Integer VERIFY_CODE_EXPIRE_TIME = 30;
	
	//头像文件上传大小控制   1024*1024
	public final static Long HEAD_IMAGE_MAX_FILE_SIZE = 1048576L;
}
