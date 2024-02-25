package com.stocks.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.stocks.user.Student;
import com.stocks.user.User;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	Optional<Student> findByEmail(String email);

	Optional<Student> findByPhoneNumber(String phoneNumber);

	Optional<Student> findByRollNumber(String rollNumber);

	@Query("SELECT s FROM Student s WHERE FUNCTION('MONTH', s.dateOfBirth) = ?1 AND FUNCTION('DAY', s.dateOfBirth) = ?2")
	List<Student> findByMonthAndDayOfBirth(int month, int day);

	List<Student> findByDateOfBirth(LocalDate today);
}
