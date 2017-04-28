package cn.aplus.resp;


/**
 * Boolean数据返回，Boolean和String
 * @author PanHoucheng
 *
 */
public class BooleanAndMessageResp extends BaseResult{
	private Boolean flag;
	private String message;
	public Boolean getFlag() {
		return flag;
	}
	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "BooleanAndMessageResp [flag=" + flag + ", message=" + message
				+ "]";
	}
	
}
