package mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import po.User;
import provider.UserPro;

/**
 *
 * @Description: 添加描述
 *
 * @author: ZLK
 * @date: 2018/6/28 16:37
 *
 */
public interface UserMapper {

	@SelectProvider(method="select",type=UserPro.class)
    User selcet(Map<String, String> param);
	
	@Insert("insert into tb_user values(#{username},#{nickname},#{password},#{mail},#{name},#{address})")
    int add(User u);

	@UpdateProvider(method="update",type=UserPro.class)
    int change(Map<String, Object> param);
}
