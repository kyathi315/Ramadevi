package com.stocks.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stocks.user.Student;
import com.stocks.user.User;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	Optional<Student> findByEmail(String email);
	Optional<Student> findByPhoneNumber(String phoneNumber);
	Optional<Student> findByRollNumber(String rollNumber);
}
