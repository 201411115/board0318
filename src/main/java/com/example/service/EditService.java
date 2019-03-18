package com.example.service;

import javax.servlet.http.HttpSession;

import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Users;
import com.example.repository.UserRepository;

@Service
public class EditService {

	@Autowired
	private UserRepository userrepository;

	@Autowired
	private UserPasswordHash userpasswordhash;
	@Autowired
	HttpSession session;

	public String editUser(String userid, String userpw, String newid, String newpw, String newname) {

		if (userid.equals("") || userpw.equals("")) {
			return "index";
		}
		String hashpass = userpasswordhash.getSHA256(userpw);
		Users users = new Users();
		System.out.println(userid + userpw);

		// System.out.println(users.getUsernum());
		String adaptid = newid;
		System.out.println(adaptid);
		String adaptpw = newpw;
		System.out.println(adaptpw);
		String adaptname = newname;
		System.out.println(adaptname);
		// users = (Users) session.getAttribute("loginUser");
		users = userrepository.findByUseridAndPassword(userid, userpw);
		hashpass = userpasswordhash.getSHA256(adaptpw);
		// System.out.println("its users" + users.getUserid());

		users.setUserid(adaptid);
		users.setPassword(hashpass);
		users.setUsername(adaptname);
		userrepository.save(users);
		return "index";

	}
}
