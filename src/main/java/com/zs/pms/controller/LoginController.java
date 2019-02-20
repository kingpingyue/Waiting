package com.zs.pms.controller;


import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.code.kaptcha.Constants;
import com.zs.pms.po.TPermission;
import com.zs.pms.po.TUser;
import com.zs.pms.service.UserSerivce;
import com.zs.pms.utils.DateUtil;
import com.zs.pms.utils.MD5;
import com.zs.pms.vo.QueryLogin;
import com.zs.pms.vo.QueryUser;

@Controller // 是一个控制器
public class LoginController {
	@Autowired
	UserSerivce us;
	
	@RequestMapping("/tologin.do")
	// 登录页面
	public String tologin() {
		return "login";
	}
	
	@RequestMapping("/login.do")
	// 去默认页面
	public String login(QueryLogin login,HttpSession session,ModelMap model) {
		//1、验证验证码
		String ocode=(String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
		//生成验证码和页面验证码不相等
		if (!ocode.equalsIgnoreCase(login.getChkcode())) {
			model.addAttribute("MSG", "验证码不匹配请重新输入");
			return "login";
	}
		//2、验证账号密码
		//装载数据
		QueryUser query=new QueryUser();
		//登录名
		query.setLoginname(login.getUsername());
		//MD5加密
		MD5 md5=new MD5();
		//密码
		query.setPassword(md5.getMD5ofStr(login.getPassword()));
		//设置可用
		query.setIsenabled(1);
		//返回登录的用户
		 List<TUser> users=us.queryByCon(query);
		 if (users==null||users.size()!=1) {
			model.addAttribute("MSG", "账号或密码不正确，请重新输入！");
			return "login";
		}
		 //登录成功 装session
		 session.setAttribute("CUSER", users.get(0));
		 //去默认页面
		return "main";
	}
	@RequestMapping("/top.do")
	// 去顶部
	public String top(ModelMap model) {
		//将当前时间返回给页面
		model.addAttribute("TODAY",DateUtil.getStrDate(new Date()));
		return "top";
	}
	
	
	@RequestMapping("/main.do")
	//
	public String main() {
		return "main";
	}
	@RequestMapping("/right.do")
	//右侧页面
	public String right() {
		return "right";
	}
	
	
	@RequestMapping("/left.do")
	//左侧页面
	public String left(HttpSession session,ModelMap model) {
		System.out.println("哇哈哈");
		//获得当前登录用户
		TUser cu=(TUser) session.getAttribute("CUSER");
		//获得该用户的权限列表
		System.out.println(1+"葫芦娃"+cu.getId());
		List<TPermission> list1=us.queryByUid(cu.getId());
		//返回菜单
		model.addAttribute("MENU",us.getMenu(list1));
		return "left";
	}
}
