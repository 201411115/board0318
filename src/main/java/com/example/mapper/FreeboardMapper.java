package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.model.Freeboard;

@Mapper
public interface FreeboardMapper {
	public Freeboard selectByFreeboardid(Long freeid);
	// public Freeboard selectByFreeid(@Param("freeid") Long freeid);

}
