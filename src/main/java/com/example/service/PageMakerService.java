package com.example.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.example.model.Freeboard;
import com.example.pageMaker.PageMaker;

@Service
public class PageMakerService {

	public PageMaker generatePageMaker(int pageNum, int contentNum, JpaRepository<Freeboard, Long> repository) {
		PageMaker pageMaker = new PageMaker();

		int totalcount = (int) repository.count();
		pageMaker.setTotalCount(totalcount);
		pageMaker.setPageNum(pageNum - 1);
		pageMaker.setContentNum(contentNum);
		pageMaker.setCurrentBlock(pageNum);
		pageMaker.setLastBlock(pageMaker.getTotalCount());
		pageMaker.prevnext(pageNum);
		pageMaker.setStartPage(pageMaker.getCurrentBlock());
		pageMaker.setEndPage(pageMaker.getLastBlock(), pageMaker.getCurrentBlock());

		return pageMaker;

	}
}
