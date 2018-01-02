package cn.controller;

import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.pojo.User;
import cn.service.user.UserService;
import cn.tools.Constants;

@Controller
@RequestMapping(value="/user")
public class UserController {
	@Resource
	private UserService userService;
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value="/login.html")
	public String login(String userCode,String userPassword,HttpSession session){
		User user=userService.login(userCode, userPassword);
		session.setAttribute(Constants.USER_SESSION, user);
		Enumeration<String> e = session.getAttributeNames();
        while (e.hasMoreElements()) {
            String s = e.nextElement();
            System.out.println(s + " == " + session.getAttribute(s));
        }

		if(user!=null){
			if(user.getId()>0){
				return "frame";
			}else{
				return "redirect:/login.jsp";
			}
		}else{
			return "redirect:/login.jsp";
		}
		
	}
	
}
