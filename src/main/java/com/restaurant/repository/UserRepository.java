package com.restaurant.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.entity.Role;
import com.restaurant.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	 Optional<User> findByEmail(String email);

	    boolean existsByEmail(String email);
	    List<User> findByRole(Role role);
}
