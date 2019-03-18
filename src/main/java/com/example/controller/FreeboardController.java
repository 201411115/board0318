package com.example.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.mapper.FreeboardMapper;
import com.example.model.Freeboard;
import com.example.service.FreeboardListService;
import com.example.service.FreeboardInfoService;
import com.example.service.FreeboardWriteService;

@Controller
public class FreeboardController {

	@Autowired
	private FreeboardListService freeboardListService;
	@Autowired
	private FreeboardWriteService freeboardWriteService;
	@Autowired
	private FreeboardInfoService freeboardInfoService;
	@Autowired
	private FreeboardMapper freeboardMapper;

	private int returnIntValue(String stringToint) {
		return Integer.parseInt(stringToint);

	}

	@GetMapping("/freeboard") // default value를 지정하여 오류를 줄인다.
	public String freeboard(@RequestParam(value = "pageNum", defaultValue = "1") String pageNum) {
		String page = freeboardListService.freeboardList(returnIntValue(pageNum));

		return page;
	}

	// usercontroller 와 동일 만드는 순서와 방법을 숙지하는 것이 중요
	@PostMapping("/freeboardWriteRequest")
	public String freeboardWriteRequest(@RequestParam Map<String, String> paraMap) {

		String title = paraMap.get("title");
		String content = paraMap.get("content");
		String writer = paraMap.get("writer");
		// 셋다 전달하고 redirect

		freeboardWriteService.write(title, content, writer);

		// default 값이 1 글을 쓰고나서 자유게시판 글을 포함해서 다시보이게됨
		return "redirect:/freeboard";
	}

	@GetMapping("/freeboardInfo")
	public String getBoard(@RequestParam(value = "freeid") String freeid, HttpSession session) {
		Long Lfreeid = Long.parseLong(freeid);
		Freeboard freeboard = freeboardMapper.selectByFreeboardid(Lfreeid);
		if (freeboard == null) {
			return "freeBoardInfo";
		}
		session.setAttribute("freeboard", freeboard);
		return "freeBoardInfo";
		// String page = freeboardInfoService.getFreeboardPost(freeid);

	}

	// @ResponseBody
	@RequestMapping(value = "/api/freeboardInfo", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject getPost(@RequestParam(value = "freeid") String freeid) {
		Long Lfreeid = Long.parseLong(freeid);
		Freeboard freeboard = freeboardMapper.selectByFreeboardid(Lfreeid);
		if (freeboard == null) {
			return null;
		}
		JSONObject obj = freeboardInfoService.getFreeboardPost(freeboard);

		return obj;
		// String page = freeboardInfoService.getFreeboardPost(freeid);

	}

//	@RequestMapping(value = "/ajax.seo", method = RequestMethod.GET)
//	public void AjaxView(@RequestParam("id") String id, HttpServletResponse response) {
//		ObjectMapper mapper = new ObjectMapper();
//
//		SocialPerson person = dao.getPerson(id);
//		try {
//			response.getWriter().print(mapper.writeValueAsString(person));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

}
