package controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import po.User;
import service.IMessageService;
import tool.MessageSelect;
import tool.ResultMap;

import javax.servlet.http.HttpSession;

@Controller
public class MessageController {

	@Autowired
	private IMessageService msgDao;

	/**
	 *
	 * @Description: 查询用户消息列表
	 *
	 * @param messageSelect 查询条件
	 * @return 查询到的消息列表
	 *
	 * @version: v1.0.0
	 * @author: ZXJ
	 * @date: 2018/6/26 9:01
	 *
	 * Modification History:
	 * Date         Author          Version            Description
	 *---------------------------------------------------------*
	 * 2018/6/26      zhoulk          v1.0.0             新建
	 */
	@RequestMapping(value = "/getMessage", produces = "text/plain;charset=utf-8")
	public @ResponseBody String getMessage(MessageSelect messageSelect,HttpSession session) {
		String username = ((User) session.getAttribute("user")).getUsername();
		messageSelect.setUsername(username);
		return JSON.toJSONString(msgDao.getMessage(messageSelect));
	}

	/**
	 *
	 * @Description: 把消息状态改为已读
	 *
	 * @param
	 * @return
	 *
	 * @version: v1.0.0
	 * @author: ZXJ
	 * @date: 2018/6/26 9:02
	 *
	 * Modification History:
	 * Date         Author          Version            Description
	 *---------------------------------------------------------*
	 * 2018/6/26      zhoulk          v1.0.0             新建
	 */
	@RequestMapping("/readMessage")
	public @ResponseBody void changeMessage(String id) {
		msgDao.readMessage(id);
	}

	/**
	 *
	 * @Description: 删除该条消息
	 *
	 * @param messageId 消息id
	 * @return 删除结果
	 *
	 * @version: v1.0.0
	 * @author: ZXJ
	 * @date: 2018/6/26 9:06
	 *
	 * Modification History:
	 * Date         Author          Version            Description
	 *---------------------------------------------------------*
	 * 2018/6/26      zhoulk          v1.0.0             新建
	 */
	@RequestMapping(value = "/deleteMsg",produces = "application/json;charset=utf-8")
	public @ResponseBody String deleteMsg(String messageId) {
		ResultMap<String> resultMap = msgDao.deletMessage(messageId);
		return JSON.toJSONString(resultMap);
	}

	/**
	 *
	 * @Description: 返回用户未读消息数量
	 *
	 * @param
	 * @return
	 *
	 * @version: v1.0.0
	 * @author: ZXJ
	 * @date: 2018/6/26 9:24
	 *
	 * Modification History:
	 * Date         Author          Version            Description
	 *---------------------------------------------------------*
	 * 2018/6/26      zhoulk          v1.0.0             新建
	 */
	@RequestMapping("/unread")
	public @ResponseBody String unread(HttpSession session) {
		String username = ((User) session.getAttribute("user")).getUsername();
		return ""+msgDao.unread(username);
		
	}

}
