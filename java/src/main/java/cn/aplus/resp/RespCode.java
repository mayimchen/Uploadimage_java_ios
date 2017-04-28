package cn.aplus.resp;

public enum RespCode {
	//系统相关
	NODEFINE(0, "NODEFINE"), 
	PASS_OK(1000, "Successful"), 
	INVALID_FIELD(1001, "param error"),
	BAD_REQUEST(1002,"bad request"),
	SERVICE_NOT_FOUND(1003,"service not found"),
	SIGN_ERROR(1004,"sign error."),
	HTTPS_PROTOCOL_ERROR(1005,"only support https."),
	TOKEN_EXPIRED(1006,"token expired."),
	UNAUTHORIZED(1007,"Unauthorized"),
	ACCOUNT_EXIST(1008,"account exist"),
	NO_DATA(1009,"no data"),
	EX_SERVER_INNER(1010,"server inner error."),
	EX_NOT_TRUE(1101,"EX_NOT_TRUE."),
	PARAM_FORMAT_ERROR(1200,"user param fromat error"),
	
	//用户相关
	USER_NOT_FIND(2001,"user not find"),
	USER_PASSWORD_ERROR(2002,"user password error"),
	USER_ID_PARAM_ERROR(2003,"user id error"),
	USER_UPLOAD_IMAGE_SIZE_MAX(2100,"user upload head image file size max"),

	//验证码相关，隶属于用户
	USER_VCODE_VALIDATE_ERROR(2100,"user forget password vcode validate error."),
	
	//学校相关
	COLLEGE_ID_IS_NOT_EXIST(2500,"college id is not find."),
	
	EX_WELCOME_ONLY(3001,"EX_WELCOME_ONLY"),
	
	//教师课程相关
	TEACHER_ADD_COURSE_STUDENT_NUM_ERROR(4000,"add course student num error"),
	TEACHER_ADD_COURSE_SUBJECT_ERROR(4001,"add course subject error"),
	TEACHER_ADD_COURSE_TIME_ERROR(4002,"add course time error"),
	TEACHER_ADD_COURSE_ERROR(4003,"add course error"),
	TEACHER_ADD_COURSE_WEEK_NUM_ERROR(4004,"add course student num error"),;
	

	public int code;
	public String message;

	public String getMessage() {
		return message;
	}

	RespCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return this.code;
	}
}
