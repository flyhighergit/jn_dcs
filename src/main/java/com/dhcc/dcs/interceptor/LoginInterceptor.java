package com.dhcc.dcs.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dhcc.dcs.entity.TSBaseUser;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 从session中获取登录的用户信息
		Object userObj = request.getSession().getAttribute("user");
		if (userObj != null) {
			TSBaseUser user = (TSBaseUser) userObj;
			// 判断用户是否为司机用户
			if (user != null && user.getId() != null 
				&& user.getStatus() == 1 && "4".equals(user.getBsUserType())
				&& !"1".equals(user.getDeleteFlag()))
				return true;
		}
		
		// 不满足条件的话，则退回到登录界面
		/* PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<script>");
		out.println("window.open ('" + request.getContextPath() + "/driverlogin/login','_top')");
		out.println("</script>");
		out.println("</html>"); */
		response.sendRedirect(request.getContextPath() + "/driverlogin/login");
		
		return false;
	}
}
