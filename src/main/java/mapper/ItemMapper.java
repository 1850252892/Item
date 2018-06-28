package mapper;

import model.ItemDetails;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import po.Item;
import tool.ItemSelect;

import java.util.List;

/**
 *
 * @Description: 添加描述
 *
 * @author: ZXJ
 * @date: 2018/6/28 16:33
 *
 */
public interface ItemMapper {

	@Select("select * from tb_item where id=#{id}")
	Item selectById(String id);

	@Select("select * from tb_item,tb_item_other where tb_item.id=#{id} and tb_item.id=tb_item_other.id")
	ItemDetails selectDetails(String id);

	List<Item> select(ItemSelect select);

	Integer selectCount(ItemSelect select);

	List<String> selectClass(ItemSelect select);
	
	@Insert("insert into tb_item values(#{id},#{time},#{name},#{detail},#{img},#{expect},#{classification},#{price},#{uid})")
	int add(Item i);
	
	@Delete("delete from tb_item where id=#{id}")
	int delete(String id);

	@Select("select * from tb_item where id in ("
			+ "select id from ("
			+ "select i.id from tb_item i left join collect c on i.id=c.g_id where i.id=c.g_id GROUP BY i.id ORDER BY count(g_id) DESC limit 5"
			+ ") m"
			+ ")")
	List<Item> selectPoplarItems();
}
