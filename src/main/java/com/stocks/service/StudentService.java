package com.stocks.service;

import java.util.List;

import com.stocks.DTO.StudentDTO;

public interface StudentService {
	List<StudentDTO> findAll();
    StudentDTO findById(Long id);
    StudentDTO save(StudentDTO studentDTO);
    void deleteById(Long id);
	void checkAndSendBirthdayEmails();
}
