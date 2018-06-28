package service;

import java.util.Map;

import po.User;

public interface IUserService {

	String login(User u);
	
	String register(User u);

    void changeInfo(Map<String, Object> p);
	
	User getUser(Map<String, String> p);
}
