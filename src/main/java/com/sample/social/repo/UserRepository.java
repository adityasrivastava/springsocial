package com.sample.social.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.social.dao.User;

public interface UserRepository extends JpaRepository<User, Long>{

	 public User findByEmail(String email);
}
