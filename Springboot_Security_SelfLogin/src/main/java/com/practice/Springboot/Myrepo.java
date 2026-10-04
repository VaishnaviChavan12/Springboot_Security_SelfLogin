package com.practice.Springboot;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Myrepo extends JpaRepository<User, Integer> {

	User findByUsername(String username);
}
