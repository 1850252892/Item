package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class URLController {

    @RequestMapping("/selectPage")
    public String selectPage() {
        return "itemPage/selectPage";
    }

    @RequestMapping("/otherUser")
    public String otherUser() {
        return "itemPage/otherUser";
    }

    @RequestMapping("/item/addItemPage")
    public String addItemPage() {
        return "itemPage/addItem";
    }

    @RequestMapping("/admin/myCollectPage")
    public String myCollectPage() {
        return "itemPage/myCollectPage";
    }

    @RequestMapping("/admin/mySubmitPage")
    public String mySubmitPage() {
        return "itemPage/mySubmitPage";
    }

    @RequestMapping("/admin/myItem")
    public String myItem() {
        return "itemPage/myItemPage";
    }

    @RequestMapping("/admin/businessPage")
    public String business() {
        return "itemPage/businessPage";
    }


    @RequestMapping("/item/swap")
    public String exchangePage() {
        return "itemPage/excPage";
    }

    @RequestMapping("/item/excSuc")
    public String returnExcSuc() {
        return "itemPage/excSuc";
    }

    @RequestMapping("/itemAdmin")
    public String returnItemAdmin() {
        return "itemPage/itemAdmin";
    }

    @RequestMapping("/item")
    public String geturlparam() {
        return "itemPage/itemPage";
    }

    @RequestMapping("/admin/userMainPage")
    public String userMainPage() {
        return "userPage/userMainPage";
    }

    @RequestMapping("/admin/pending")
    public String pending() {
        return "itemPage/pending";
    }

    @RequestMapping("/admin/message")
    public String message() {
        return "userPage/message";
    }


}

