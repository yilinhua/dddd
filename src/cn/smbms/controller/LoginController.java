package cn.smbms.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.smbms.pojo.User;
import cn.smbms.service.user.UserService;
import cn.smbms.tools.Constants;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/login.html")
	public String login(String userCode,String userPassword,HttpSession session,Model model){
		User user = userService.login(userCode,userPassword);
		if(null != user){//登录成功
			//放入session
			session.setAttribute(Constants.USER_SESSION, user);
			//页面跳转（frame.jsp）
			return "frame";
		}else{
			//页面跳转（login.jsp）带出提示信息--转发
			model.addAttribute("error", "用户名或密码不正确");
			return "foward:/login.jsp";
		}
	}
	
}
