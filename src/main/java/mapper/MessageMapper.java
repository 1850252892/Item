package mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import po.Message;
import tool.MessageSelect;

import java.util.List;

/**
 *
 * @Description: TODO
 *
 * @param
 * @return
 *
 * @version: v1.0.0
 * @author: ZXJ
 * @date: 2018/6/28 16:32
 *
 */
public interface MessageMapper {

	@Insert("insert into tb_message values(#{id},#{username},#{date},#{type},#{message},#{state},#{path})")
	int insert(Message m);

	List<Message> select(MessageSelect messageSelect);
	
	@Update("update tb_message set state=1 where id=#{id}")
	int update(String id);
	
	@Delete("delete from tb_message where id=#{id}")
	int delete(String messageId);
	
	@Select("select count(*) from tb_message where username=#{uid} and state=0")
	int unread(String uid);
}
