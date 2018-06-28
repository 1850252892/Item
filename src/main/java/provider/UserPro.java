package provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class UserPro {

	public String select(final Map<String, String> p){
		return new SQL(){
			{
				SELECT("*");
				FROM("tb_user");
				if(p.get("username")!=null)
					WHERE("username=#{username}");
				if(p.get("nickname")!=null)
					WHERE("nickname=#{nickname}");
				if(p.get("password")!=null)
					WHERE("password=#{password}");
				if(p.get("mail")!=null)
					WHERE("mail=#{mail}");
				if(p.get("name")!=null)
					WHERE("name=#{name}");
			}
		}.toString();
	}
	
	public String update(final Map<String, Object> p){
		return new SQL(){
			{
				UPDATE("tb_user");
				if(p.get("nickname")!=null)
					SET("nickname=#{nickname}");
				if(p.get("password")!=null)
					SET("password=#{password}");
				if(p.get("mail")!=null)
					SET("mail=#{mail}");
				if(p.get("name")!=null)
					SET("name=#{name}");
				WHERE("username=#{username}");
			}
		}.toString();
	}
}
