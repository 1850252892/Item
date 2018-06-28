package service;

import java.util.List;
import java.util.Map;

import po.Message;
import tool.MessageSelect;
import tool.ResultMap;

public interface IMessageService {

	/**
	 *
	 * @Description: 获取消息列表
	 *
	 * @param
	 * @return
	 *
	 * @version: v1.0.0
	 * @author: ZXJ
	 * @date: 2018/6/28 15:54
	 */
	List<Message> getMessage(MessageSelect messageSelect);

	/**
	 *
	 * @Description: 把消息状态改为已读
	 *
	 * @param
	 * @return
	 *
	 * @version: v1.0.0
	 * @author: ZXJ
	 * @date: 2018/6/28 15:54
	 */
	int readMessage(String id);

	/**
	 *
	 * @Description: 删除消息
	 *
	 * @param
	 * @return
	 *
	 * @version: v1.0.0
	 * @author: ZXJ
	 * @date: 2018/6/28 15:52
	 */
	ResultMap<String> deletMessage(String messageId);

	/**
	 *
	 * @Description: 获取未读消息数量
	 *
	 * @param
	 * @return
	 *
	 * @version: v1.0.0
	 * @author: ZXJ
	 * @date: 2018/6/28 15:51
	 */
	int unread(String uid);
}
