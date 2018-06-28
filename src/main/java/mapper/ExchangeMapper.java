package mapper;

import model.ExcDate;
import org.apache.ibatis.annotations.*;
import po.Exchange;

import java.util.List;
import java.util.Map;

/**
 *
 * @Description: 添加描述
 *
 * @author: ZLK
 * @date: 2018/6/28 16:35
 *
 */
public interface ExchangeMapper {
	
	@Insert("insert into tb_exchange values(#{id},#{uid_a},#{uid_b},#{gid_a},#{gid_b},#{date},#{info},#{state})")
    int addExc(Exchange exc);


	@Select("select * from tb_exchange where id=#{id}")
    Exchange selectExc(String id);
	

	@Update("update tb_exchange set state=#{state} where id=#{id}")
    int changeExc(Map<String, String> m);
	
	@Select("SELECT e.id , e.date , e.gid_a , e.gid_b , i1.name gname_a , i1.img img_a , i2.name gname_b , i2.img img_b ,e.info ,e.state "
			+ "from tb_exchange e,tb_item i1,tb_item i2 "
			+ "where e.id=#{eid} and (i1.id=e.gid_a and i2.id=e.gid_b)")
	@ResultMap("ecxDate")
    ExcDate selectExcAllDate(String eid);


    @Select("SELECT e.id , e.date , e.gid_a , e.gid_b , i1.name gname_a , i1.img img_a , i2.name gname_b , i2.img img_b ,e.info ,e.state "
			+ "from tb_exchange e,tb_item i1,tb_item i2 "
			+ "where e.uid_a like '${uid_a}' and e.uid_b like '${uid_b}' and e.gid_a=i1.id and e.gid_b=i2.id and e.state=0	")
	@ResultMap("ecxDate")
    List<ExcDate> selectMyExc(@Param("uid_a") String uid_a, @Param("uid_b") String uid_b);

	@Select("SELECT e.id , e.date , e.gid_a , e.gid_b , i1.name gname_a , i1.img img_a , i2.name gname_b , i2.img img_b ,e.info ,e.state "
			+ "from tb_exchange e,tb_item i1,tb_item i2 "
			+ "where (e.uid_a=#{id} or e.uid_b=#{id}) and e.gid_a=i1.id and e.gid_b=i2.id and (state=1 or state=-1)")
	@ResultMap("ecxDate")
    List<ExcDate> selectSuc(String id);
}
