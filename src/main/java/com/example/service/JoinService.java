package com.example.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.Users;
import com.example.repository.UserRepository;

@Service
public class JoinService {
	// 알아서 객체주입
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserPasswordHash userPasswordHash;

	public String joinUser(String userId, String userPw, String userName) {

		if (userId.equals("") || userPw.equals("") || userName.equals("")) {
			return "index";
		}

		System.out.println(userId);
		System.out.println(userName);
		System.out.println(userPw);

		Users users = new Users();
		users.setUserid(userId);
		users.setUsername(userName);
		String hashpass = userPasswordHash.getSHA256(userPw);
		users.setPassword(hashpass);

		userRepository.save(users);

		return "index";

	}

}
