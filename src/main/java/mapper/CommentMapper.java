package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import po.Comment;
/**
 *
 * @Description: 添加描述
 *
 * @author: PSY
 * @date: 2018/6/28 16:36
 *
 */
public interface CommentMapper {

	@Insert("insert into comment values(#{u_id},#{g_id},#{info},#{date})")
    int insert(Comment comment);
	
	@Delete("delete from comment where u_id=#{uid} and g_id=#{gid}")
    int delete(@Param("uid") String uid, @Param("gid") String gid);
	
	@Select("select * from comment where g_id=#{gid}")
    List<Comment> find(String gid);
	
	@Select("select * from comment where g_id=#{gid}")
    Integer getCount(String gid);
}
