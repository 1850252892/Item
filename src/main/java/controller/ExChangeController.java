package controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import po.Exchange;
import po.Item;
import po.User;
import service.IExchangeService;
import service.IItemService;
import tool.CustomFormatter;
import tool.ItemSelect;
import tool.ResultMap;
import tool.UUIDUtils;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Administrator
 */
@Controller
@RequestMapping("/item")
public class ExChangeController {

    @Autowired
    private IExchangeService es;
    @Autowired
    private IItemService is;

    /**
     * @param exc 交换单数据
     * @return 操作结果以及交换单号
     * @Description: 提交交换数据
     * @version: v1.0.0
     * @author: ZLK
     * @date: 2018/6/26 9:54
     * <p>
     * Modification History:
     * Date         Author          Version            Description
     * ---------------------------------------------------------*
     * 2018/6/26      zhoulk          v1.0.0             新建
     */
    @RequestMapping(value = "/itemExc", produces = "application/json;charset=utf-8")
    public @ResponseBody
    String itemExc(Exchange exc, HttpSession session) {
        exc.setId(UUIDUtils.getUUID());
        exc.setDate(CustomFormatter.fomartterDateToString("yyyy-MM-dd HH:mm", new Date()));
        exc.setState(Exchange.SUBMIT);
        exc.setUid_a(((User) session.getAttribute("user")).getUsername());

        Item item_b = is.selectById(exc.getGid_b());
        String uid_b = item_b.getUid();
        exc.setUid_b(uid_b);

        ResultMap<String> result = es.addExc(exc);
        return JSON.toJSONString(result);
    }

    /**
     * @param id    交换单id
     * @param state 处理结果(1:同意,-1:拒绝)
     * @return 操作结果
     * @Description: /isExchange
     * @version: v1.0.0
     * @author: ZLK
     * @date: 2018/6/26 9:56
     * <p>
     * Modification History:
     * Date         Author          Version            Description
     * ---------------------------------------------------------*
     * 2018/6/26      zhoulk          v1.0.0             新建
     */
    @RequestMapping(value = "/isExchange", produces = "application/json;charset=utf-8")
    public @ResponseBody
    String exchangeSuccess(@RequestParam("eId") String id, @RequestParam("state") String state, HttpSession session) {
        Map<String, String> m = new HashMap<>();
        m.put("id", id);
        m.put("state", state);
        return JSON.toJSONString(es.changeExc(m));
    }

    /**
     * @param type 交换类型
     * @return 交换单列表
     * @Description: 获取用户的交换列表，包括自己提交的交换请求以及别人提交给自己的交换请求
     * @version: v1.0.0
     * @author: ZLK
     * @date: 2018/6/28 14:33
     * <p>
     * Modification History:
     * Date         Author          Version            Description
     * ---------------------------------------------------------*
     * 2018/6/28      zhoulk          v1.0.0             新建
     */
    @RequestMapping(value = "/getExchange", produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String getSwapList(HttpSession session, @RequestParam("type") String type) {
        if (type.equals("submit")) {//自己提交的交换
            return JSON.toJSONString(es.getMyExc(((User) session.getAttribute("user")).getUsername(), "%"));
        } else if (type.equals("request"))//别人请求自己的交换
            return JSON.toJSONString(es.getMyExc("%", ((User) session.getAttribute("user")).getUsername()));
        return "";
    }

    /**
     * @param session 获取用户信息
     * @return 返回历史交易记录
     * @Description: 获取历史交易记录，包括交换成功的以及交换失败的
     * @version: v1.0.0
     * @author: ZLK
     * @date: 2018/6/28 14:35
     * <p>
     * Modification History:
     * Date         Author          Version            Description
     * ---------------------------------------------------------*
     * 2018/6/28      zhoulk          v1.0.0             新建
     */
    @RequestMapping(value = "/getSuc", produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String getSuccessSwap(HttpSession session) {

        return JSON.toJSONString(es.getSuc(((User) session.getAttribute("user")).getUsername()));
    }

    /**
     * @param excId 交换单id
     * @return 交换单数据
     * @Description: 获取一条交换记录的具体信息
     * @version: v1.0.0
     * @author: ZLK
     * @date: 2018/6/28 14:36
     * <p>
     * Modification History:
     * Date         Author          Version            Description
     * ---------------------------------------------------------*
     * 2018/6/28      zhoulk          v1.0.0             新建
     */
    @RequestMapping(value = "/getExcData", produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String getExcData(@RequestParam("excId") String excId) {
        return JSON.toJSONString(es.getExcAllDate(excId));
    }

    /**
     * @param excId 交换单id
     * @return 操作结果
     * @Description: 取消本次交换
     * @version: v1.0.0
     * @author: ZLK
     * @date: 2018/6/28 14:37
     * <p>
     * Modification History:
     * Date         Author          Version            Description
     * ---------------------------------------------------------*
     * 2018/6/28      zhoulk          v1.0.0             新建
     */
    @RequestMapping(value = "/cancelExc", produces = "application/json;charset=utf-8")
    public @ResponseBody
    String remove(@RequestParam("eId") String excId) {

        return JSON.toJSONString(es.deleteExc(excId));
    }
    /**
     *
     * @Description: 获取用户的自由商品列表
     *
     * @param uid 用户id
     * @return 商品列表
     *
     * @version: v1.0.0
     * @author: ZLK
     * @date: 2018/6/28 14:49
     *
     * Modification History:
     * Date         Author          Version            Description
     *---------------------------------------------------------*
     * 2018/6/28      zhoulk          v1.0.0             新建
     */
    @RequestMapping(value = "/getFreeItem", produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String getFreeItem(@RequestParam("uid") String uid) {
        ItemSelect s = new ItemSelect();
        s.setUid(uid);
        s.setStatus("FREE");
        s.setOrder("time");
        s.setIsDesc("desc");
        return JSON.toJSONString(is.find(s));
    }

}
