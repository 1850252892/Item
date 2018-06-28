package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import model.ItemOwner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.alibaba.fastjson.JSON;

import po.Item;
import po.User;
import po.UserRole;
import security.MyUserDetail;
import service.IItemService;
import service.IUserService;
import tool.ItemSelect;

@Controller
@SessionAttributes("user")
public class UserController {

    @Autowired
    private IUserService u;
    @Autowired
    private IItemService itemService;

    @RequestMapping(value = "/loginPage")
    public String loginPage() {
        return "userPage/loginPage";
    }

    /**
     *
     * @Description: 获取用户数据，包括个人信息以及商品列表
     *
     * @param username 用户帐号
     * @return 用户相关信息
     *
     * @version: v1.0.0
     * @author: zhou_lk
     * @date: 2018/6/28 14:51
     *
     * Modification History:
     * Date         Author          Version            Description
     *---------------------------------------------------------*
     * 2018/6/28      zhoulk          v1.0.0             新建
     */
    @RequestMapping(value = "/user/details", produces = "application/json;charset=utf-8")
    public @ResponseBody
    String getUserDetails(String username) {
        Map<String, String> p = new HashMap<>();
        p.put("username", username);
        User user = u.getUser(p);

        ItemSelect is = new ItemSelect();
        is.setUid(user.getUsername());
        is.setStatus("FREE");
        List<Item> list = itemService.find(is);
        return JSON.toJSONString(new ItemOwner(user, list));
    }


    //用户验证成功操作
    @RequestMapping("/loginSuccess")
    public @ResponseBody
    String login(@RequestParam("url") String url, Model model, HttpServletRequest request) {
        //获取用户权限信息实例
        MyUserDetail userDetails = (MyUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserRole myUser = userDetails.getMyUser();

        //添加当前用户实例到session
        Map<String, String> m = new HashMap<String, String>();
        m.put("username", myUser.getUsername());
        User user = u.getUser(m);
        model.addAttribute("user", user);

        //返回ajax请求信息
        Map<String, String> map = new HashMap<String, String>();
        map.put("success", "true");
        map.put("url", url);
        return JSON.toJSONString(map);

    }

    //用户验证失败操作
    @RequestMapping(value = "/login/failure", produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String failure() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("success", "false");
        map.put("msg", "账户名或密码错误");
        return JSON.toJSONString(map);
    }

    @RequestMapping("/mainPage")
    public String loginSuccess(Model model) {
        return "main";
    }

    @RequestMapping(value = "/registerPage")
    public String registerPage(Model model) {
        return "userPage/registerPage";
    }

    @RequestMapping("/register")
    public @ResponseBody
    String resgiter(User user) {
        return u.register(user);
    }

    /**
     *
     * @Description: 修改用户信息
     *
     * @param uMap 要修改的用户信息
     * @return 操作结构
     *
     * @version: v1.0.0
     * @author: zhou_lk
     * @date: 2018/6/28 14:52
     *
     * Modification History:
     * Date         Author          Version            Description
     *---------------------------------------------------------*
     * 2018/6/28      zhoulk          v1.0.0             新建
     */
    @RequestMapping("/admin/changeInfo")
    public @ResponseBody
    String changeInfo(@RequestBody Map<String, Object> uMap, Model model) {
        Map<String, Object> m = new HashMap<>();
        Map<String, Object> p = uMap;
        m.put("username", p.get("username"));
        if (p.get("nickname") != "")
            m.put("nickname", p.get("nickname"));
        if (p.get("mail") != "")
            m.put("mail", p.get("mail"));
        if (p.get("name") != "")
            m.put("name", p.get("name"));
        if (p.get("password") != "") {
            if (p.get("oldpassword") != null) {
                String msg = u.login(new User((String) p.get("username"), (String) p.get("oldpassword")));
                if (msg.equals("FALSE"))
                    return "oldpasserro";
                m.put("password", p.get("password"));
            }
        }
        u.changeInfo(m);
        Map<String, String> m1 = new HashMap<String, String>();
        m1.put("username", (String) p.get("username"));
        model.addAttribute("user", u.getUser(m1));
        return "success";
    }


    @RequestMapping("/userQuit")
    public String userQuit(Model model) {
        model.addAttribute("user", new User());
        return "userPage/loginPage";
    }
    @RequestMapping("/admin/excData")
    public String excData() {
        return "itemPage/excData";
    }

}
