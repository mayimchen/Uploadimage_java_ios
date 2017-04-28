package cn.aplus.resp;


/**
 * 简单数据返回，如String,Int,Long,Double,Boolean等，只有一个返回值
 * @author PanHoucheng
 *
 */
public class SimpleResp extends BaseResult{
	private String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String toString() {
		return super.toString()+"SimpleResp [result=" + result + "]";
	}
	
}
