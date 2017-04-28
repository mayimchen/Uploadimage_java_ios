package cn.aplus.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 公用帮助类
 * 
 * @author PanHoucheng
 */
public class CommonUtils {

	private static Logger logger = LoggerFactory.getLogger(CommonUtils.class);

	public static String getCurrentDate() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}

	// 计算两个时间相隔多少个月
	public static int getMonthDistance(Date start, Date end) {
		if (start.after(end)) {
			Date t = start;
			start = end;
			end = t;
		}
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(start);
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(end);
		Calendar temp = Calendar.getInstance();
		temp.setTime(end);
		temp.add(Calendar.DATE, 1);

		int year = endCalendar.get(Calendar.YEAR)
				- startCalendar.get(Calendar.YEAR);
		int month = endCalendar.get(Calendar.MONTH)
				- startCalendar.get(Calendar.MONTH);

		if ((startCalendar.get(Calendar.DATE) == 1)
				&& (temp.get(Calendar.DATE) == 1)) {
			return year * 12 + month + 1;
		} else if ((startCalendar.get(Calendar.DATE) != 1)
				&& (temp.get(Calendar.DATE) == 1)) {
			return year * 12 + month;
		} else if ((startCalendar.get(Calendar.DATE) == 1)
				&& (temp.get(Calendar.DATE) != 1)) {
			return year * 12 + month;
		} else {
			return (year * 12 + month - 1) < 0 ? 0 : (year * 12 + month);
		}
	}

	/**
	 * 通过反射, 获得Class定义中声明的父类的泛型参数的类型. 如无法找到, 返回Object.class. eg. public UserDao
	 * extends HibernateDao<User>
	 * 
	 * @param clazz
	 *            The class to introspect
	 * @return the first generic declaration, or Object.class if cannot be
	 *         determined
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> Class<T> getSuperClassGenricType(final Class clazz) {
		return getSuperClassGenricType(clazz, 0);
	}

	/**
	 * 通过反射, 获得Class定义中声明的父类的泛型参数的类型. 如无法找到, 返回Object.class.
	 * 
	 * 如public UserDao extends HibernateDao<User,Long>
	 * 
	 * @param clazz
	 *            clazz The class to introspect
	 * @param index
	 *            the Index of the generic ddeclaration,start from 0.
	 * @return the index generic declaration, or Object.class if cannot be
	 *         determined
	 */
	@SuppressWarnings({ "rawtypes" })
	public static Class getSuperClassGenricType(final Class clazz,
			final int index) {

		Type genType = clazz.getGenericSuperclass();

		if (!(genType instanceof ParameterizedType)) {
			logger.warn(clazz.getSimpleName()
					+ "'s superclass not ParameterizedType");
			return Object.class;
		}

		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

		if (index >= params.length || index < 0) {
			logger.warn("Index: " + index + ", Size of "
					+ clazz.getSimpleName() + "'s Parameterized Type: "
					+ params.length);
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			logger.warn(clazz.getSimpleName()
					+ " not set the actual class on superclass generic parameter");
			return Object.class;
		}

		return (Class) params[index];
	}

	/** 十六进制下数字到字符的映射数组 */
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	/**
	 * 把inputString加密。
	 * 
	 * @param des
	 *            加密秘钥
	 * @param inputString
	 *            待加密的字符串
	 * @return
	 */
	public static String createPassword(String des, String password) {
		return MD5.md5(des, password);
	}

	/**
	 * 验证输入的密码是否正确
	 * 
	 * @param password
	 *            真正的密码（加密后的真密码）
	 * @param des
	 *            加密秘钥
	 * @param inputString
	 *            输入的字符串
	 * @return
	 */
	public static boolean authenticatePassword(String password, String des,
			String inputString) {
		if (MD5.md5(des, inputString).equals(password)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 对字符串进行MD5编码
	 * 
	 * @param originString
	 * @return
	 */
	public static String encodeByMD5(String originString) {
		if (originString != null) {
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				byte[] results = md.digest(originString.getBytes());
				String resultString = byteArrayToHexString(results);
				return resultString.toUpperCase();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 转换字节数组为16进制字串
	 * 
	 * @param b
	 *            字节数组
	 * @return 十六进制字串
	 */
	private static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	/**
	 * 将一个字节转化成16进制形式的字符串
	 * 
	 * @param b
	 * @return
	 */
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static String getImageStr(InputStream in) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		byte[] data = null;
		// 读取图片字节数组
		try {
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		return Base64.encode(data);// 返回Base64编码过的字节数组字符串
	}

	public static boolean generateImage(String imgStr, String filename) {// 对字节数组字符串进行Base64解码并生成图片
		if (imgStr == null) // 图像数据为空
			return false;
		// BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = Base64.decode(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			// 生成jpeg图片
			OutputStream out = new FileOutputStream(filename);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@SuppressWarnings("rawtypes")
	public static boolean isEmptyCollection(Collection c) {
		if (c == null || c.size() < 1)
			return true;
		return false;
	}

	/**
	 * 验证是否邮箱
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		if (StringUtils.isBlank(email))
			return false;
		return Pattern
				.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")
				.matcher(email).matches();
	}

	/**
	 * 验证是否手机号码
	 * 
	 * @param mobile
	 * @return
	 */
	public static boolean isMobile(String mobile) {
		if (StringUtils.isBlank(mobile))
			return false;
		return Pattern.compile("^[1][3,4,5,8][0-9]{9}$").matcher(mobile)
				.matches(); // 验证手机号
	}

	/**
	 * 电话号码验证
	 * 
	 * @param str
	 * @return 验证通过返回true
	 */
	public static boolean isPhone(String phone) {
		Pattern p1 = null, p2 = null;
		Matcher m = null;
		boolean b = false;
		p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$"); // 验证带区号的
		p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$"); // 验证没有区号的
		if (phone.length() > 9) {
			m = p1.matcher(phone);
			b = m.matches();
		} else {
			m = p2.matcher(phone);
			b = m.matches();
		}
		return b;
	}

	/**
	 * 生成6位随机号码
	 * 
	 * @return
	 */
	public static String getSixRandCode(boolean numberFlag, int length) {
		String retStr = "";
		String strTable = numberFlag ? "1234567890"
				: "1234567890ABCDEFGHIJKMNPQRSTUVWXYZ";
		int len = strTable.length();
		boolean bDone = true;
		do {
			retStr = "";
			int count = 0;
			for (int i = 0; i < length; i++) {
				double dblR = Math.random() * len;
				int intR = (int) Math.floor(dblR);
				char c = strTable.charAt(intR);
				if (('0' <= c) && (c <= '9')) {
					count++;
				}
				retStr += strTable.charAt(intR);
			}
			if (count >= 2) {
				bDone = false;
			}
		} while (bDone);

		return retStr;
	}

	/**
	 * 检查字符串是否为数字
	 * 
	 * @param str
	 *            指定的字符串
	 * @return true/false
	 */
	public static boolean isNum(String str) {
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}

	/**
	 * 是否是网址
	 * 
	 * @param url
	 *            指定的网址
	 * @return true/false 是网址/不是网址
	 */
	public static boolean isHomepage(String url) {
		boolean flag = false;
		String regex = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(url);
		flag = matcher.matches();
		return flag;
	}

	/**
	 * 验证输入身份证号
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 true,否则为 false
	 */
	public static boolean IsIDcard(String str) {
		String regex = "(^\\d{18}$)|(^\\d{15}$)";
		return match(regex, str);
	}

	/**
	 * 验证是不是QQ号
	 * 
	 * @param str
	 *            待验证的qq
	 * @return 如果是符合格式的qq,返回 true,否则为 false
	 */
	public static boolean IsQq(String str) {
		String regex = "[1-9][0-9]{4,14}";
		return match(regex, str);
	}

	/**
	 * 验证是不是邮政编号
	 * 
	 * @param str
	 *            需要验证的邮政编号
	 * @return 如果是邮编，返回true,否则返回false
	 */
	public static boolean IsPostcode(String str) {
		String regex = "^\\d{6}$";
		return match(regex, str);
	}

	/**
	 * 验证是不是date 如：YYYY-MM-DD
	 * 
	 * @param str
	 *            待验证的date
	 * @return 如果是符合格式的date,返回 true,否则为 false
	 */
	public static boolean IsDate(String str) {
		String regex = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]? ((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
		return match(regex, str);
	}

	/**
	 * 验证是否为空字符串：空则返回true，否则返回false
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNUllStr(String str) {
		boolean res = true;
		if (str != null && !str.equals("")) {
			res = false;
		}
		return res;
	}

	/**
	 * @param regex
	 *            正则表达式字符串
	 * @param str
	 *            要匹配的字符串
	 * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
	 */
	private static boolean match(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}

	public static int indexOfIgnoreCase(String body, String p) {
		p = p.toLowerCase();
		char[] source = body.toCharArray();
		int sourceOffset = 0;
		int sourceCount = source.length;
		char[] target = p.toCharArray();
		int targetOffset = 0;
		int targetCount = target.length;
		int fromIndex = 0;

		if (fromIndex >= sourceCount) {
			return (targetCount == 0 ? sourceCount : -1);
		}
		if (fromIndex < 0) {
			fromIndex = 0;
		}
		if (targetCount == 0) {
			return fromIndex;
		}

		char first = target[targetOffset];
		int i = sourceOffset + fromIndex;
		int max = sourceOffset + (sourceCount - targetCount);

		startSearchForFirstChar: while (true) {

			/* Look for first character. */
			while (i <= max && Character.toLowerCase(source[i]) != first) {
				i++;
			}
			if (i > max) {
				return -1;
			}

			/* Found first character, now look at the rest of v2 */
			int j = i + 1;
			int end = j + targetCount - 1;
			int k = targetOffset + 1;
			while (j < end) {
				if (Character.toLowerCase(source[j++]) != target[k++]) {
					i++;
					/* Look for str's first char again. */
					continue startSearchForFirstChar;
				}
			}
			return i - sourceOffset; /* Found whole string. */
		}
	}

	/**
	 * 获取当前时间前几天或后几天的时间
	 * days参数为向前或向后的天数，正为后，负为前
	 * */
	public static Date changeDate(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, days);
		return calendar.getTime();
	}

	/**
	 * 生成一个UUID
	 * 
	 * @return str UUID
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 去掉UUID中的'-'
	 * 
	 * @return UUID
	 */
	public static String getUUIDRemoveLine() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public static List<Long> convertStringToLongList(String ids) {
		if (StringUtils.isBlank(ids))
			return new ArrayList<Long>();

		List<Long> delIds = new ArrayList<Long>();
		for (String id : ids.split(",")) {
			try {
				Long delId = Long.valueOf(id);
				delIds.add(delId);
			} catch (Exception e) {
				continue;
			}
		}
		return delIds;
	}
}
