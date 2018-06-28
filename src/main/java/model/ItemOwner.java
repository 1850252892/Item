package model;

import po.Item;
import po.User;

import java.util.List;

/**
 * @ClassName: ItemOwner
 * @Description: 添加描述
 * @author: zhoulk
 * @date: 2018/6/27 10:40
 * <p>
 * Modification History:
 * Date         Author            Description
 * ---------------------------------------------------------*
 * 2018/6/27      zhoulk            新建
 */

public class ItemOwner {
    private User user;
    private List<Item> list;

    public ItemOwner() {
    }

    public ItemOwner(User user, List<Item> list) {
        this.user = user;
        this.list = list;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Item> getList() {
        return list;
    }

    public void setList(List<Item> list) {
        this.list = list;
    }
}
