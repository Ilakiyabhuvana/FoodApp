package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Userlogin;


@Repository
public interface Userloginrepo extends JpaRepository<Userlogin, Integer>  {
	@Query("SELECT u FROM Userlogin u WHERE u.username =?1 AND u.password =?2")
	public List<Userlogin> findByLogin(String username, String password);

}
