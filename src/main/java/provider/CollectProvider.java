package provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class CollectProvider {

	public String select(final Map<String, String> m){
		return new SQL(){
			{
				SELECT("*");
				FROM("collect");
				if(m.get("uid")!=null)
					WHERE("u_id=#{uid}");
				if(m.get("gid")!=null)
					WHERE("g_id=#{gid}");
			}
		}.toString();
	}
}
