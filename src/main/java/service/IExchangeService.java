package service;

import java.util.List;
import java.util.Map;

import model.ExcDate;
import po.Exchange;
import tool.ResultMap;

public interface IExchangeService {

	/**
	 *
	 * @Description: 新建一条交换记录
	 *
	 * @param exc 交换记录数据
	 * @return 操作结果以及返回数据，插入成功返回交换单id，失败返回失败原因
	 *
	 * @version: v1.0.0
	 * @author: ZLK
	 * @date: 2018/6/28 15:35
	 *
	 */
	 ResultMap<String> addExc(Exchange exc);

	/**
	 *
	 * @Description: 取消交换
	 *
	 * @param id 交换单id
	 * @return
	 *
	 * @version: v1.0.0
	 * @author: ZLK
	 * @date: 2018/6/28 15:36
	 *
	 */
	 ResultMap<String> deleteExc(String id);

	/**
	 *
	 * @Description: 改变价换单状态，即同意或拒绝
	 *
	 * @param m 交换单id以及修改的状态
	 * @return 操作结果以及提示信息
	 *
	 * @version: v1.0.0
	 * @author: ZLK
	 * @date: 2018/6/28 15:40
	 *
	 */
	 ResultMap<String> changeExc(Map<String,String> m);

	 /**
	  *
	  * @Description: 获取交换单具体信息
	  *
	  * @param eid 交换单id
	  * @return ExcData
	  *
	  * @version: v1.0.0
	  * @author: ZLK
	  * @date: 2018/6/28 15:41
	  *
	  */
	 ExcDate getExcAllDate(String eid);

	/**
	 * @param uid_a 如果查询自己提交的交换单信息就设置为uid,uid_b设置为%
	 * @param uid_b 如果查询自己是被请求方的交换单信息就设置为uid,uid_a设置为%
	 * @return
	 */
	 List<ExcDate> getMyExc(String uid_a, String uid_b);

	/**
	 *
	 * @Description: 获取用户历史交易记录
	 *
	 * @param uid 用户id
	 * @return 交易记录列表
	 *
	 * @version: v1.0.0
	 * @author: ZLK
	 * @date: 2018/6/28 15:43
	 *
	 */
	 List<ExcDate> getSuc(String uid);
}
