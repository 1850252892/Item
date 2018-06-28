package po;

import java.util.List;

/**
 *
 * @ClassName: po.Item
 * @Description: 商品信息
 *
 * @author: zhoulk
 * @date: 2018/6/28 15:03
 *
 * Modification History:
 * Date         Author            Description
 *---------------------------------------------------------*
 * 2018/6/28      zhoulk            新建
 */
public class Item {

	private String id;//商品id
	private String time;//创建时间
	private String name;//商品名
	private String detail;//上平信息
	private String img;//图片
	private List<String> imgpath;//图片地址列表
	private String expect;//期待商品
	private String classification;//商品分类

	private Integer price;//商品参考价格
	private String uid;//用户id

	public Item(String id, String time, String name, String detail, String img,
			String expect, String classification, Integer price, String uid) {
		super();
		this.id = id;
		this.time = time;
		this.name = name;
		this.detail = detail;
		this.img = img;
		this.expect = expect;
		this.classification = classification;
		this.price = price;
		this.uid = uid;
	}

	public Item() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public List<String> getImgpath() {
		return imgpath;
	}
	public void setImgpath(List<String> imgpath) {
		this.imgpath = imgpath;
	}

	public String getExpect() {
		return expect;
	}

	public void setExpect(String expect) {
		this.expect = expect;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	
}
