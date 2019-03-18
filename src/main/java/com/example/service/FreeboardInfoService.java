package com.example.service;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mapper.FreeboardMapper;
import com.example.model.Freeboard;
import com.example.repository.FreeboardRepository;

@Service
public class FreeboardInfoService {

	@Autowired
	private FreeboardRepository freeboardRepository;
	@Autowired
	private HttpSession session;

	@Autowired
	private FreeboardMapper freeboardMapper;

	public JSONObject getFreeboardPost(Freeboard freeboard) {

		JSONObject obj = new JSONObject();

		obj.put("freeid", freeboard.getFreeid());
		obj.put("title", freeboard.getTitle());
		obj.put("content", freeboard.getContent());
		obj.put("writer", freeboard.getWriter());
		return obj;
	}

}
