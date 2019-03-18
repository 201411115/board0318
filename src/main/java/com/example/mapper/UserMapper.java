package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.model.Users;

@Mapper
public interface UserMapper {
	public Users selectByUseridAndPassword(String userid, String userpw);

}
