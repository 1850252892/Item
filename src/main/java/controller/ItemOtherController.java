package controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import po.Collect;
import po.Comment;
import po.Item;
import po.User;
import service.ICollectService;
import service.ICommentService;
import service.IItemService;
import tool.ResultMap;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: ItemOtherController
 * @Description: 添加描述
 * @author: PSY
 * @date: 2018/6/28 16:07
 * <p>
 * Modification History:
 * Date         Author            Description
 * ---------------------------------------------------------*
 * 2018/6/28      PSY            新建
 */

@Controller
public class ItemOtherController {
    @Autowired
    private ICollectService collectd;
    @Autowired
    private IItemService i;
    @Autowired
    private ICommentService commentd;

    @RequestMapping("/getRecommend")
    public @ResponseBody
    List<Item> getRecommend() {
        return i.getPopular();
    }

    /**
     * @Description: 收藏商品
     *
     * @param itemid 要收藏的商品id
     * @return 返回收藏结果以及收藏数
     *
     * @version: v1.0.0
     * @author: PSY
     * @date: 2018/6/28 14:27
     * <p>
     * Modification History:
     * Date         Author          Version            Description
     * ---------------------------------------------------------*
     * 2018/6/28      zhoulk          v1.0.0             新建
     */
    @RequestMapping("/item/collect")
    public @ResponseBody
    String collect(@RequestParam("itemid") String itemid, HttpSession session) {
        Collect c = new Collect(((User) session.getAttribute("user")).getUsername(), itemid);
        ResultMap<Integer> result = collectd.addCollect(c);
        return JSON.toJSONString(result);
    }

    /**
     *
     * @Description: 取消收藏
     *
     * @param itemid 要取消收藏的商品id
     * @return 操作结果以及收藏数
     *
     * @version: v1.0.0
     * @author: PSY
     * @date: 2018/6/28 14:28
     * <p>
     * Modification History:
     * Date         Author          Version            Description
     * ---------------------------------------------------------*
     * 2018/6/28      zhoulk          v1.0.0             新建
     */
    @RequestMapping("/item/cancelCollect")
    public @ResponseBody
    String cancelCollect(@RequestParam("itemid") String itemid, HttpSession session) {
        String uid = ((User) session.getAttribute("user")).getUsername();
        ResultMap<Integer> result = collectd.deleteCollect(uid, itemid);
        return JSON.toJSONString(result);
    }

    /**
     * @Description: 获取用户的收藏商品的列表
     *
     * @param session 获取session中的用户帐号
     * @return 返回用户的收藏列表
     *
     * @version: v1.0.0
     * @author: PSY
     * @date: 2018/6/28 14:29
     * <p>
     * Modification History:
     * Date         Author          Version            Description
     * ---------------------------------------------------------*
     * 2018/6/28      zhoulk          v1.0.0             新建
     */
    @RequestMapping(value = "/item/getCollect", produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String getCollect(HttpSession session) {
        return JSON.toJSONString(collectd.getMyCollectItem(((User) session.getAttribute("user")).getUsername()));
    }

    /**
     *  @Description: 判断用户是否收藏该商品
     *
     * @param itemid 商萍id
     * @return 是否收藏
     *
     * @version: v1.0.0
     * @author: PSY
     * @date: 2018/6/28 14:30
     * <p>
     * Modification History:
     * Date         Author          Version            Description
     * ---------------------------------------------------------*
     * 2018/6/28      zhoulk          v1.0.0             新建
     */
    @RequestMapping("/isCollect")
    public @ResponseBody
    String isCollect(@RequestParam("itemid") String itemid, HttpSession session) {

        String uid = ((User) session.getAttribute("user")).getUsername();
        if (collectd.select(uid, itemid) != null)
            return "exist";
        return "no";
    }


    /**
     *  @Description: 提交评论
     *
     * @param comment 评论相关信息
     * @return 返回操作结果
     *
     * @version: v1.0.0
     * @author: PSY
     * @date: 2018/6/28 14:31
     * <p>
     * Modification History:
     * Date         Author          Version            Description
     * ---------------------------------------------------------*
     * 2018/6/28      zhoulk          v1.0.0             新建
     */
    @RequestMapping("/comment")
    public @ResponseBody
    String comment(Comment comment) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        comment.setDate(sdf.format(date));
        if (commentd.addComment(comment) != 0)
            return "success";
        return "false";
    }


    /**
     * @Description: 获取商萍的评论列表
     *
     * @param itemid 商萍id
     * @return 评论列表
     *
     * @version: v1.0.0
     * @author: PSY
     * @date: 2018/6/28 14:32
     * <p>
     * Modification History:
     * Date         Author          Version            Description
     * ---------------------------------------------------------*
     * 2018/6/28      zhoulk          v1.0.0             新建
     */
    @RequestMapping(value = "/getComment", produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String getComment(@RequestParam("itemid") String itemid) {
        List<Comment> commentList = commentd.findComment(itemid);
        return JSON.toJSONString(commentList);
    }
}
