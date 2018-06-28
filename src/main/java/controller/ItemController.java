package controller;

import com.alibaba.fastjson.JSON;
import model.ItemDetails;
import model.ItemOwner;
import model.ItemsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import po.Item;
import po.User;
import service.IItemService;
import service.IUserService;
import tool.CustomFormatter;
import tool.ItemParse;
import tool.ItemSelect;
import tool.UUIDUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes(value = {"user", "additem",})
@RequestMapping(value = "/item", produces = "application/json;charset=utf-8")
public class ItemController {

    @Autowired
    private IItemService i;
    @Autowired
    private IUserService u;

    @RequestMapping("/addSuccessPage")
    public String addSuccess(@ModelAttribute("additem") Item item, Model model) {
        model.addAttribute("additem", item);
        return "itemPage/addSuccessPage";
    }

    /**
     *
     * @Description: 上传商品
     *
     * @param item 商品信息
     * @return 上传结果.
     *
     * @version: v1.0.0
     * @author: PSY
     * @date: 2018/6/28 14:42
     *
     * Modification History:
     * Date         Author          Version            Description
     *---------------------------------------------------------*
     * 2018/6/28      zhoulk          v1.0.0             新建
     */
    @RequestMapping("/uploadItem")
    public @ResponseBody
    String upload(Model model, Item item) {
        item.setId(UUIDUtils.getUUID());
        item.setTime(CustomFormatter.fomartterDateToString(CustomFormatter.baseFormat,new Date()));
        item.setUid(((User) model.asMap().get("user")).getUsername());
        boolean flag = i.upload(item);
        if (flag == false)
            return "FALSE";
        else {
            ItemSelect is = new ItemSelect();
            is.setId(item.getId());
            item = i.find(is).get(0);
            model.addAttribute("additem", item);
            return "success";
        }
    }

    /**
     *
     * @Description: 下架商品
     *
     * @param itemId 商品id
     * @return 操作结果
     *
     * @version: v1.0.0
     * @author: PSY
     * @date: 2018/6/28 14:45
     *
     * Modification History:
     * Date         Author          Version            Description
     *---------------------------------------------------------*
     * 2018/6/28      zhoulk          v1.0.0             新建
     */
    @RequestMapping(value = "/deleteItem")
    public @ResponseBody
    String down(@RequestParam("itemId") String itemId) {
        return JSON.toJSONString(i.down(itemId));
    }

    /**
     *
     * @Description: 获取分类列表
     *
     * @param
     * @return 分类列表
     *
     * @version: v1.0.0
     * @author: PSY
     * @date: 2018/6/28 14:50
     *
     * Modification History:
     * Date         Author          Version            Description
     *---------------------------------------------------------*
     * 2018/6/28      zhoulk          v1.0.0             新建
     */
    @RequestMapping(value = "/class_list", produces = "application/json;charset=utf-8")
    public @ResponseBody
    String getClassList() {
        return JSON.toJSONString(i.getClassList());
    }


    /**
     *
     * @Description: 获取商品详情
     *
     * @param id 商品id
     * @return 商品详情
     *
     * @version: v1.0.0
     * @author: PSY
     * @date: 2018/6/28 14:47
     *
     * Modification History:
     * Date         Author          Version            Description
     *---------------------------------------------------------*
     * 2018/6/28      zhoulk          v1.0.0             新建
     */
    @RequestMapping(value = "/details")
    public @ResponseBody
    String getItemDetails(String id) {
        ItemDetails item = i.selectItemDetails(id);
        item.setImgpath(ItemParse.parseItemImg(item.getImg()));
        return JSON.toJSONString(item);
    }

    /**
     *
     * @Description: 获取商品拥有者信息，包括拥有者身份信息以及其所发布的其它商品列表
     *
     * @param id 商品id
     * @return ItemOwner
     *
     * @version: v1.0.0
     * @author: PSY
     * @date: 2018/6/28 14:47
     *
     * Modification History:
     * Date         Author          Version            Description
     *---------------------------------------------------------*
     * 2018/6/28      zhoulk          v1.0.0             新建
     */
    @RequestMapping(value = "/owner")
    public @ResponseBody
    String getItemOwner(String id) {
        Item item = i.selectById(id);
        Map<String, String> p = new HashMap<>();
        p.put("username", item.getUid());
        User user = u.getUser(p);

        ItemSelect is = new ItemSelect();
        is.setUid(user.getUsername());
        is.setStartLine(0);
        is.setEndLine(4);
        is.setStatus("FREE");
        List<Item> list = i.find(is);
        return JSON.toJSONString(new ItemOwner(user, list));

    }


    /**
     *
     * @Description: 获取商品列表
     *
     * @param is 商品查询条件
     * @return 商品列表（ItemModel）
     *
     * @version: v1.0.0
     * @author: ZXJ
     * @date: 2018/6/28 14:46
     *
     * Modification History:
     * Date         Author          Version            Description
     *---------------------------------------------------------*
     * 2018/6/28      zhoulk          v1.0.0             新建
     */
    @RequestMapping(value = "/getItemList", produces = "application/json;charset=utf-8")
    public @ResponseBody
    String find(@RequestBody ItemSelect is) {
        ItemsModel im = new ItemsModel(i.find(is), i.itemCount(is), i.selectClass(is));
        return JSON.toJSONString(im);
    }

}
