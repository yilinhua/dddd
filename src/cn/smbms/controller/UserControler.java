package cn.smbms.controller;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



import cn.smbms.pojo.Role;
import cn.smbms.pojo.User;
import cn.smbms.service.role.RoleService;
import cn.smbms.service.user.UserService;

import cn.smbms.tools.Constants;
import cn.smbms.tools.PageSupport;

@Controller
@RequestMapping("/user")
public class UserControler {
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	@Autowired
	@Qualifier("roleService")
	private RoleService roleService;
	
	
	@RequestMapping(value="/selUser.html")
	public String Select(@RequestParam(value="queryname",required=false) String queryname,@RequestParam(value="queryUserRole",required=false) String queryUserRole,
			@RequestParam(value="pageIndex",required=false) String pageIndex,
			Model model){
		int _queryUserRole =0;
		List<User> userList = null;
		//设置页面容量
    	int pageSize = Constants.pageSize;
    	//当前页码
    	int currentPageNo = 1;
		
		System.out.println("queryUserName servlet--------"+queryname);  
		System.out.println("queryUserRole servlet--------"+queryUserRole);  
		System.out.println("query pageIndex--------- > " + pageIndex);
		if(queryname == null){
			queryname = "";
		}
		if(queryUserRole!=null&&!queryUserRole.equals("")){
			_queryUserRole = Integer.parseInt(queryUserRole);
		}
    	if(pageIndex != null){
    		try{
    			currentPageNo = Integer.valueOf(pageIndex);
    		}catch(NumberFormatException e){
    		
    		}
    	}	
    	//总数量（表）	
    	int totalCount	= userService.getUserCount(queryname,_queryUserRole);
    	//总页数
    	PageSupport pages=new PageSupport();
    	pages.setCurrentPageNo(currentPageNo);
    	pages.setPageSize(pageSize);   
    	pages.setTotalCount(totalCount);
    	int totalPageCount = pages.getTotalPageCount();
    	//控制首页和尾页
    	if(currentPageNo < 1){
    		currentPageNo = 1;
    	}else if(currentPageNo > totalPageCount){
    		currentPageNo = totalPageCount;
    	}
		userList = userService.getUserList(queryname,_queryUserRole,currentPageNo, pageSize);
		model.addAttribute("userList", userList);
		List<Role> roleList = null;
		roleList = roleService.getRoleList();
		model.addAttribute("roleList", roleList);
		model.addAttribute("queryUserName", queryname);
		model.addAttribute("queryUserRole", _queryUserRole);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("currentPageNo", currentPageNo);
		return "userlist";
	}
	public String insert(User user){
		Boolean bool=userService.add(user);
		if(bool!=null){	
		}
		return null;
		
	}
	
	
	
}
