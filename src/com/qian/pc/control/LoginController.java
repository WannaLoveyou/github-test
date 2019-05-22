package com.qian.pc.control;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/login")
public class LoginController {

	private static final String LOGIN_PAGE = "login";

	private static Logger log = Logger.getLogger(LoginController.class);

	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String login() {
		return LOGIN_PAGE;
	}
	
	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	public String doLogin(HttpServletRequest request, Map<String, Object> map)  {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		try {
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		token.setRememberMe(true);
		Subject subject = SecurityUtils.getSubject();
		subject.getSession().setTimeout(43200000);
			subject.login(token);

			if (subject.isAuthenticated()) {
				return "redirect:/index/index";
			} else {
				map.put("msg", "提示：您输入的帐号密码存在错误");
				return "login";
			}
		} catch (IncorrectCredentialsException e) {
			log.error(e);
		} catch (UnknownAccountException e) {
			log.error(e);
		}catch(Exception e){
			map.put("msg", "提示：您输入的帐号密码存在错误");
			return "login";
		}
		map.put("msg", "提示：您输入的帐号密码存在错误");
		return "login";
	}
}
