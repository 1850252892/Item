package po;

/**
 *
 * @ClassName: po.Comment
 * @Description: 评论
 *
 * @author: zhoulk
 * @date: 2018/6/28 15:01
 *
 * Modification History:
 * Date         Author            Description
 *---------------------------------------------------------*
 * 2018/6/28      zhoulk            新建
 */
public class Comment {
	private String u_id;// 用户id
	private String g_id;// 商品id
	private String info;// 评论信息
	private String date;// 评论时间

	public Comment(){
	}
	
	public Comment(String u_id, String g_id) {
		super();
		this.u_id = u_id;
		this.g_id = g_id;
	}

	public String getU_id() {
		return u_id;
	}

	public void setU_id(String u_id) {
		this.u_id = u_id;
	}

	public String getG_id() {
		return g_id;
	}

	public void setG_id(String g_id) {
		this.g_id = g_id;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
