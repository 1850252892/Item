package model;

import po.Item;

import java.util.List;

public class ItemsModel {
    private List<Item> list;
    private List<String> classList;
    private Integer count;

    public ItemsModel(List<Item> list, Integer count, List<String> classList) {
        this.list = list;
        this.classList=classList;
        this.count = count;
    }

    public List<Item> getList() {
        return list;
    }

    public void setList(List<Item> list) {
        this.list = list;
    }

    public List<String> getClassList() {
        return classList;
    }

    public void setClassList(List<String> classList) {
        this.classList = classList;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
