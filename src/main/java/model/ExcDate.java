package model;
/**
 *
 * @ClassName: po.ExcDate
 * @Description: 添加描述
 *
 * @author: zhoulk
 * @date: 2018/6/28 14:59
 *
 * Modification History:
 * Date         Author            Description
 *---------------------------------------------------------*
 * 2018/6/28      zhoulk            新建
 */
public class ExcDate {
	private String excId;
	private String excDate;
	private String gid_a;
	private String gname_a;
	private String img_a;
	private String img_b;
	private String gid_b;
	private String gname_b;
	private String info;
	private Integer state;

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getImg_a() {
		return img_a;
	}

	public void setImg_a(String img_a) {
		this.img_a = img_a;
	}

	public String getImg_b() {
		return img_b;
	}

	public void setImg_b(String img_b) {
		this.img_b = img_b;
	}

	
	public String getExcId() {
		return excId;
	}

	public void setExcId(String excId) {
		this.excId = excId;
	}

	public String getExcDate() {
		return excDate;
	}

	public void setExcDate(String excDate) {
		this.excDate = excDate;
	}

	public String getGid_a() {
		return gid_a;
	}

	public void setGid_a(String gid_a) {
		this.gid_a = gid_a;
	}

	public String getGname_a() {
		return gname_a;
	}

	public void setGname_a(String gname_a) {
		this.gname_a = gname_a;
	}

	public String getGid_b() {
		return gid_b;
	}

	public void setGid_b(String gid_b) {
		this.gid_b = gid_b;
	}

	public String getGname_b() {
		return gname_b;
	}

	public void setGname_b(String gname_b) {
		this.gname_b = gname_b;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}


}
