package com.example.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.mapper.FreeboardMapper;
import com.example.mapper.UserMapper;
import com.example.model.Freeboard;
import com.example.model.Users;
import com.example.repository.UserRepository;
import com.example.service.EditService;
import com.example.service.JoinService;

import com.example.service.LoginService;
import com.example.service.UserPasswordHash;

@Controller
public class UserController {
	@Autowired
	private JoinService joinService;
	@Autowired
	private LoginService LoginService;
	@Autowired
	private EditService editService;
	@Autowired
	private UserPasswordHash userpasswordhash;
	@Autowired
	private UserMapper userdMapper;

	@Autowired
	private UserRepository userrepository;

	// 컨트롤러에서 메핑된 url을 통해 가입요청을 보내는 작djq
	@PostMapping(path = "/joinRequest")
	public String joinRequest(@RequestParam Map<String, String> paraMap) {
		String userId = paraMap.get("user_id");
		String userPw = paraMap.get("user_pw");
		String userName = paraMap.get("user_name");
		System.out.println(userId + " " + userPw + " " + userName);

		String page = joinService.joinUser(userId, userPw, userName);

		return page;

	}

	@PostMapping(path = "/loginRequest")
	public String loginRequest(@ModelAttribute Users user, @Autowired HttpSession session,
			@RequestParam Map<String, String> paraMap) {

		// String page = joinService.joinUser(userId, userPw, userName);

		user.setUserid(paraMap.get("user_id"));
		String hashpass = userpasswordhash.getSHA256(paraMap.get("user_pw"));
		user.setPassword(hashpass);
		if (user.getUserid() == null || user.getPassword() == null) {
			return "/login";
		}
		session.setAttribute("loginUser", user);

		String page = LoginService.login(user);

		return page;
	}

	@PostMapping(path = "/editRequest")
	public String editRequest(@ModelAttribute Users user, @Autowired HttpSession session,
			@RequestParam Map<String, String> paraMap) {
		String userId = user.getUserid().toString();
		String userPw = user.getPassword().toString();
		String userName = user.getUsername().toString();
		System.out.println("controller" + userId + " " + userPw + " " + userName);

		System.out.println(paraMap.get("newid") + "   " + paraMap.get("newpw") + " " + paraMap.get("newname"));
		String page = editService.editUser(userId, userPw, paraMap.get("newid"), paraMap.get("newpw"),
				paraMap.get("newname"));

		return page;

	}

	@RequestMapping(value = "/api/login", method = RequestMethod.GET)
	@ResponseBody
	public Users getPost(@RequestParam(value = "userid") String userid,
			@RequestParam(value = "password") String password) {

		Users user = new Users();
		user = userdMapper.selectByUseridAndPassword(userid, password);
		if (user == null) {
			return null;
		}
		return user;
		// String page = freeboardInfoService.getFreeboardPost(freeid);

	}

}
