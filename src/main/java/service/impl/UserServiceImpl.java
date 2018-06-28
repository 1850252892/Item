package service.impl;

import java.util.HashMap;
import java.util.Map;

import mapper.UserMapper;
import mapper.UserRoleMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import po.User;
import po.UserRole;
import service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserRoleMapper urm;

	@Override
	public String login(User u) {
		Map<String, String> m = new HashMap<String, String>();
		m.put("username", u.getUsername());
		m.put("password", u.getPassword());
		u = userMapper.selcet(m);
		if (u == null)
			return "FALSE";
		else
			return "SUCCESS";
	}


	@Override
	public String register(User u) {
		User uT;
		Map<String, String> m = new HashMap<String, String>();
		m.put("username", u.getUsername());
		uT = userMapper.selcet(m);
		if (uT != null)
			return "USERNA EXIST";
		m.put("nickname", u.getNickname());
		uT = userMapper.selcet(m);
		if (uT != null) {
			return "NICKNAME EXIST";
		}
		m.clear();
		m.put("mail", u.getMail());
		uT = userMapper.selcet(m);
		if (uT != null) {
			return "MAIL EXIST";
		}
		urm.insertUserRole(new UserRole(u.getUsername(),u.getPassword(),"ROLE_USER"));
		userMapper.add(u);
		return "SUCCESS";

	}


	@Override
	public void changeInfo(Map<String, Object> p) {
		userMapper.change(p);
		if(p.get("password")!=null){
			Map<String,String> map=new HashMap<>();
			map.put("password",(String)p.get("password"));
			map.put("username",(String)p.get("username"));
			urm.updataPassword(map);
		}

	}

	@Override
	public User getUser(Map<String, String> p) {

		return userMapper.selcet(p);
	}


}
