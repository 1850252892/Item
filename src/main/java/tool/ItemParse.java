package tool;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.ExcDate;
import po.Item;

public class ItemParse {
	
	/**
	 *
	 * @Description: 解析商品图片
	 *
	 * @param itemList 商品列表
	 * @return 解析完之后的商品列表
	 *
	 * @version: v1.0.0
	 * @author: zhou_lk
	 * @date: 2018/6/28 15:15
	 *
	 * Modification History:
	 * Date         Author          Version            Description
	 *---------------------------------------------------------*
	 * 2018/6/28      zhoulk          v1.0.0             新建
	 */
	static public List<Item> parseItemImg(List<Item> itemList) {
		List<Item> IL = itemList;
		Iterator<Item> i = IL.iterator();
		while (i.hasNext()) {
			Item item = i.next();
			String img = item.getImg();
			String imgs[] = img.split("\\*");
			List<String> ls = new ArrayList<String>();
			int l = imgs.length;
			for (int j = 0; j < l; j++) {
				ls.add(imgs[j]);
			}
			item.setImgpath(ls);
		}
		return IL;
	}

	/**
	 *
	 * @Description: 解析图片地址
	 *
	 * @param img 拼接后的地址
	 * @return 拆解后的地址
	 *
	 * @version: v1.0.0
	 * @author: zhou_lk
	 * @date: 2018/6/28 15:16
	 *
	 * Modification History:
	 * Date         Author          Version            Description
	 *---------------------------------------------------------*
	 * 2018/6/28      zhoulk          v1.0.0             新建
	 */
	static public List<String> parseItemImg(String img){
		String imgs[] = img.split("\\*");
		List<String> ls = new ArrayList<String>();
		int l = imgs.length;
		for (int j = 0; j < l; j++) {
			ls.add(imgs[j]);
		}
		 return ls;
	}

	static public List<ExcDate> parseExcImg(List<ExcDate> list){
		Iterator<ExcDate> i = list.iterator();
		while (i.hasNext()) {
			ExcDate item = i.next();
			String img_a = item.getImg_a();
			String imgs[] = img_a.split("\\*");
			item.setImg_a(imgs[0]);
			String img_b = item.getImg_b();
			String imgs_[] = img_b.split("\\*");
			item.setImg_b(imgs_[0]);
		}
		return list;
	}
}
