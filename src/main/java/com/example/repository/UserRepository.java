package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

	public Users findByUseridAndPassword(String userid, String userpw);

}
