package com.stocks.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stocks.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	 boolean existsByUsername(String username);
	    User findByUsername(String username);
	    boolean existsByEmail(String email);
}
