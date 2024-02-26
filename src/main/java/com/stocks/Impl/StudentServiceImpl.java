package com.stocks.Impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.stocks.DTO.StudentDTO;
import com.stocks.Exceptions.StudentException;
import com.stocks.Mapper.StudentMapper;
import com.stocks.Repository.EmailTaskRepository;
import com.stocks.Repository.StudentRepository;
import com.stocks.service.EmailService;
import com.stocks.service.StudentService;
import com.stocks.user.EmailTask;
import com.stocks.user.Student;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
    StudentRepository studentRepository;
	 @Autowired
	private EmailTaskRepository emailTaskRepository;
	 @Autowired
	 EmailService emailService;

	    @Override
	    public List<StudentDTO> findAll() {
	        return studentRepository.findAll()
	                .stream()
	                .map(StudentMapper::toDTO)
	                .collect(Collectors.toList());
	    }

	    @Override
	    public StudentDTO findById(Long id) {
	        return studentRepository.findById(id)
	                .map(StudentMapper::toDTO)
	                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
	    }

	    @Override
	    public StudentDTO save(StudentDTO studentDTO) {
	        Student student = StudentMapper.toEntity(studentDTO);
	        student = studentRepository.save(student);
	        return StudentMapper.toDTO(student);
	    }

	    @Override
	    public void deleteById(Long id) {
	        studentRepository.deleteById(id);
	    }

	    @Scheduled(cron = "0 36 12 * * ?")
	    public void checkAndSendBirthdayEmails() {
	        List<Student> todaysBirthdays = studentRepository.findByDateOfBirth(LocalDate.now());
	        for (Student student : todaysBirthdays) {
	            String subject = "Happy Birthday!";
	            String body = "Dear " + student.getName() + ", we wish you a happy birthday!";
	            emailService.sendSimpleMessage(student.getEmail(), subject, body);
	        }
	    }
}
