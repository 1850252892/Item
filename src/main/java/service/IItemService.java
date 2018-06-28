package service;

import model.ItemDetails;
import po.Item;
import tool.ItemSelect;
import tool.ResultMap;

import java.util.List;

public interface IItemService {

    /**
     *
     * @Description: 上传商品
     *
     * @param i 商品信息
     * @return 上传结果
     *
     * @version: v1.0.0
     * @author: PSY
     * @date: 2018/6/28 15:45
     *
     */
    boolean upload(Item i);

    /**
     *
     * @Description: 商品下架
     *
     * @param itemId 商品id
     * @return 操作结果以及相关提示信息
     *
     * @version: v1.0.0
     * @author: PSY
     * @date: 2018/6/28 15:46
     *
     */
    ResultMap<String> down(String itemId);

    /**
     *
     * @Description: 通过id查询商品
     *
     * @param id 商品id
     * @return 商品
     *
     * @version: v1.0.0
     * @author: PSY
     * @date: 2018/6/28 15:47
     *
     */
    Item selectById(String id);

    /**
     *
     * @Description: 通过id查询商品的各种详细信息
     *
     * @param id
     * @return ItemDetails
     *
     * @version: v1.0.0
     * @author: ZXJ
     * @date: 2018/6/28 15:48
     *
     */
    ItemDetails selectItemDetails(String id);


    /**
     *
     * @Description: 根据条件查询
     *
     * @param is 查询条件
     * @return 商品列表
     *
     * @version: v1.0.0
     * @author: ZXJ
     * @date: 2018/6/28 15:49
     *
     */
    List<Item> find(ItemSelect is);

    /**
     *
     * @Description: 查询符合条件的商品数量，主要用于分页操作
     *
     * @param
     * @return
     *
     * @version: v1.0.0
     * @author: ZXJ
     * @date: 2018/6/28 15:49
     *
     */
    Integer itemCount(ItemSelect is);

    /**
     *
     * @Description: 查询符合条件的商品有哪些分类的
     *
     * @param
     * @return
     *
     * @version: v1.0.0
     * @author: ZXJ
     * @date: 2018/6/28 15:50
     *
     */
    List<String> selectClass(ItemSelect is);

    List<Item> getPopular();

    List<String> getClassList();

}
