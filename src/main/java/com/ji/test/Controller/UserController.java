package com.ji.test.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ji.test.Domain.UserVO;
import com.ji.test.Service.UserService;

@Controller
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Inject
	private UserService uService;
	@Inject
	PasswordEncoder passwordEncoder;

	@RequestMapping("/User/LoginPage.ji")
	public String LoginPage() {
		logger.info("doA called................");
		return "user/login";
	}
	
	@RequestMapping("/User/JoinPage.ji")
	public String JoinPage() {
		return "user/join";
	}
	
	@RequestMapping("/User/Main.ji")
	public String MainPage() {
		return "user/main";
	}
	
	@RequestMapping(value = "/login")

	public ModelAndView login(

		@RequestParam(value = "error", required = false) String error,

		@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();

		if (error != null) {

			model.addObject("error", "Invalid username and password!");

		}

		if (logout != null) {

			model.addObject("msg", "You've been logged out successfully.");

		}

		model.setViewName("user/login");

		return model;

	}


	@RequestMapping("/User/Login.ji")
	@ResponseBody
	public Map<Object, Object> Login(HttpSession session, @RequestBody String json) throws Exception {
		System.out.println("안오냐");
		Map<Object, Object> map = new HashMap<Object, Object>();
		Map<String, String> userInfo = new HashMap<String, String>();

		JSONParser parser = new JSONParser();
		JSONObject json2 = (JSONObject) parser.parse(json);

		String u_id = (String) json2.get("u_id");
		String u_rpw = (String) json2.get("u_pw");
		String u_pw = uService.loginPw(u_id).getU_pw();
		if(passwordEncoder.matches(u_rpw, u_pw)) {
            System.out.println("비밀번호 일치");
            userInfo.put("u_pw", u_pw);
            map.put("pw", u_pw);
        }else {
            //============System.out.println("비밀번호 불일치");=======================
            //주석 해제 시 비 암호화 설정된 db Pw  값으로  로그인 되지 않음
        	
        }
		
		userInfo.put("u_id", u_id);
		System.out.println("id 는 "+u_id);
		UserVO user = uService.login(userInfo);
		int cnt = 0;
		
		if(user!=null) {
			map.put("result", "로그인");
			cnt = 1;
			session.setAttribute("userInfo", user);
		}else {
			int res = uService.idCheck(u_id);
			if(res==0) {
				map.put("msg", "해당 아이디가 존재하지 않습니다.");
			}else {
				map.put("msg", "비밀 번호를 다시 확인 해주세요.");
			}
		}
		map.put("cnt", cnt);
		return map;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/User/Join.ji")
	@ResponseBody
	public Map<Object, Object> Join(HttpSession session, @RequestBody String json) throws Exception {
		System.out.println("안오냐");
		Map<Object, Object> map = new HashMap<Object, Object>();
		Map<String, String> userInfo = new ObjectMapper().readValue(json, Map.class) ;
		System.out.println(userInfo.get("u_pw"));
		String encPassword = passwordEncoder.encode(userInfo.get("u_pw"));
		userInfo.put("u_pw", encPassword);
		System.out.println("비밀번호 : "+encPassword);

		int cnt = uService.join(userInfo);
		map.put("cnt", cnt);
		return map;
	}
	
	@RequestMapping("/login/accessDenied.ji")
	public String accessDenied() {
		return "user/accessDenied";
	}
}
