package com.stocks.Impl;

import java.util.List;
import java.util.stream.Collectors;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.stocks.DTO.StudentDTO;
import com.stocks.Exceptions.StudentException;
import com.stocks.Mapper.StudentMapper;
import com.stocks.Repository.StudentRepository;
import com.stocks.service.StudentService;
import com.stocks.user.Student;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
    StudentRepository studentRepository;

	 @Override
	    public List<StudentDTO> findAll() {
	        List<StudentDTO> students = studentRepository.findAll()
	                .stream()
	                .map(StudentMapper::toDTO)
	                .collect(Collectors.toList());
	        if (students.isEmpty()) {
	            throw new StudentException( "No students found");
	        }
	        return students;
	    }

	    @Override
	    public StudentDTO findById(Long id) {
	        if (id == null) {
	            throw new StudentException( "ID cannot be null");
	        }
	        Student student = studentRepository.findById(id)
	                .orElseThrow(() -> new StudentException("Student not found for this id :: " + id));
	        return StudentMapper.toDTO(student);
	    }

	    @Override
	    public StudentDTO save(StudentDTO studentDTO) {
	        Optional<Student> existingStudentByEmail = studentRepository.findByEmail(studentDTO.getEmail());
	        if (existingStudentByEmail.isPresent()) {
	            throw new StudentException("A student with the same email already exists");
	        }
	        
	        Optional<Student> existingStudentByPhoneNumber = studentRepository.findByPhoneNumber(studentDTO.getPhoneNumber());
	        if (existingStudentByPhoneNumber.isPresent()) {
	            throw new StudentException("A student with the same phone number already exists");
	        }
	        
	        Optional<Student> existingStudentByRollNumber = studentRepository.findByRollNumber(studentDTO.getRollnumber());
	        if (existingStudentByRollNumber.isPresent()) {
	            throw new StudentException("A student with the same roll number already exists");
	        }
	        
	        Student student = StudentMapper.toEntity(studentDTO);
	        student = studentRepository.save(student);
	        return StudentMapper.toDTO(student);
	    }


	    @Override
	    public void deleteById(Long id) {
	        if (id == null) {
	            throw new StudentException( "ID cannot be null");
	        }
	        if (!studentRepository.existsById(id)) {
	            throw new StudentException( "Student not found for this id :: " + id);
	        }
	        studentRepository.deleteById(id);
	    }

}
