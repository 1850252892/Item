package service;

import java.util.List;
import java.util.Map;

import po.Collect;
import po.Item;
import tool.ResultMap;

public interface ICollectService {
	/**
	 *
	 * @Description: 收藏商品
	 *
	 * @param c 收藏信息
	 * @return 操作结果以及当前商品的收藏数量
	 *
	 * @version: v1.0.0
	 * @author: PSY
	 * @date: 2018/6/28 15:28
	 *
	 */
	ResultMap<Integer> addCollect(Collect c);

	/**
	 *
	 * @Description: 用户取消对商品的收藏
	 *
	 * @param uid 用户id
	 * @param gid 商品id
	 * @return 操作结果以及该商品的收藏数量
	 *
	 * @version: v1.0.0
	 * @author: PSY
	 * @date: 2018/6/28 15:29
	 *
	 */
	ResultMap<Integer> deleteCollect(String uid, String gid);

	/**
	 *
	 * @Description: 查询一条收藏记录是否存在，用来判断该用户是否收藏了该商品
	 *
	 * @param uid 用户id
	 * @param gid 商品id
	 * @return 查询结果
	 *
	 * @version: v1.0.0
	 * @author: PSY
	 * @date: 2018/6/28 15:31
	 *
	 */
	Collect select(String uid, String gid);

	/**
	 *
	 * @Description: 获取用户收藏的商品列表
	 *
	 * @param uid 用户id
	 * @return 商品列表
	 *
	 * @version: v1.0.0
	 * @author: PSY
	 * @date: 2018/6/28 15:33
	 *
	 */
	List<Item> getMyCollectItem(String uid);
}
