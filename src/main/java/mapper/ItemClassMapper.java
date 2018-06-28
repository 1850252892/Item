package mapper;

import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 *
 * @Description: 添加描述
 *
 * @author: ZLK
 * @date: 2018/6/28 16:36
 *
 */
public interface ItemClassMapper {
    @Select("select name from tb_class order by id")
    List<String> getItemClass();
}
