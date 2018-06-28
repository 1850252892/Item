package mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import po.ItemOther;

/**
 *
 * @Description: 添加描述
 *
 * @author: PSY
 * @date: 2018/6/28 16:32
 *
 */
public interface ItemOtherMapper {

    @Insert("insert into tb_item_other(id,browser,collect,status) values (#{id},#{browser},#{collect},#{status})")
    Integer insert(ItemOther io);

    @Select("select id,browser,collect,status from tb_item_other where id=#{id}")
    ItemOther select(String id);

    @Delete("delete from tb_item_other where id=#{id}")
    Integer delete(String id);

    Integer update(ItemOther io);
}
