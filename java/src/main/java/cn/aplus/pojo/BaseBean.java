package cn.aplus.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author PanHoucheng
 * @time 2016年1月28日 下午3:27:19
 */
public class BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Date createTime = new Date();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "BaseBean [id=" + id + ", createTime=" + createTime + "]";
	}

}
