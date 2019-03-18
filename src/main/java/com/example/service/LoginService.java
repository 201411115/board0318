package com.example.service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Users;
import com.example.repository.UserRepository;

@Service
public class LoginService {

	@Autowired
	private UserRepository userrepository;

	@Autowired
	private UserPasswordHash userpasswordhash;

	@Autowired
	HttpSession session;

	public String login(Users users) {

		users = userrepository.findByUseridAndPassword(users.getUserid(), users.getPassword());

		// System.out.println(users.getUsername().toString());
		// users = boardService.findByuserIdAnduserPw(userId, userPw);

		//

		// 없으면 널이 저장됨

		if (users == null) {
			System.out.println("users null error");
			return "login";
		}
		

		return "index";

	}
}
