package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@Autowired
	private HttpSession session;

	// index페이지 가는거
	@GetMapping(path = "/")
	public String index() {
		return "index";
	}

	// 조인페이지 가기
	@GetMapping(path = "/joinpage")
	public String join() {
		return "join";
	}

	@GetMapping(path = "/loginpage")
	public String login() {
		return "login";
	}

	@GetMapping(path = "/editpage")
	public String edit() {
		return "edit";
	}

	@GetMapping(path = "/logout")
	public String logout() {
		session.invalidate();

		return "login";
	}

	@GetMapping(path = "/freeboardWritePage")
	public String freeboardWritePage() {
		return "freeboardWrite";
	}
}
