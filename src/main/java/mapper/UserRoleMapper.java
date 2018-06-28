package mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import po.UserRole;

import java.util.Map;

/**
 *
 * @Description: 添加描述
 *
 * @author: ZLK
 * @date: 2018/6/28 16:37
 *
 */
public interface UserRoleMapper {
	@Select("select *from userRole where username=#{username}")
    UserRole getUserByName(String username);
	
	@Insert("insert into userRole value(#{username},#{password},#{role})")
    void insertUserRole(UserRole u);
	
	@Update("update to userRole set password=#{password} where username=#{username}")
    void updataPassword(Map<String, String> map);
}
