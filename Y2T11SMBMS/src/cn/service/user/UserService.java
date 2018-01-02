package cn.service.user;

import cn.pojo.User;

public interface UserService {
	public User login(String userCode,String userPassword);
	
}
