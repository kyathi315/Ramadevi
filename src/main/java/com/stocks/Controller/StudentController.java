package com.stocks.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stocks.DTO.StudentDTO;
import com.stocks.Exceptions.StudentException;
import com.stocks.service.StudentService;

@RestController
@CrossOrigin("*")
@RequestMapping("/students")
public class StudentController {
	@Autowired
	StudentService studentService;

	   @GetMapping("/all")
	    public ResponseEntity<?> getAllStudents() {
	        try {
	            List<StudentDTO> students = studentService.findAll();
	            return ResponseEntity.ok(students);
	        } catch (StudentException e) {
	            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	        } catch (Exception e) {
	            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
	        try {
	            StudentDTO studentDTO = studentService.findById(id);
	            return ResponseEntity.ok(studentDTO);
	        } catch (StudentException e) {
	            return  new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	        } catch (Exception e) {
	            return  new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    @PostMapping("/save")
	    public ResponseEntity<?> saveStudent(@RequestBody StudentDTO studentDTO) {
	        try {
	            StudentDTO savedStudent = studentService.save(studentDTO);
	            return new ResponseEntity<>(savedStudent, HttpStatus.OK);
	        } catch (StudentException e) {
	            return new ResponseEntity<>( e.getMessage(),HttpStatus.BAD_REQUEST);
	        }
	    }
	    @PutMapping("/updatestu/{id}")
	    public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody StudentDTO studentDTO) {
	        try {
	            studentDTO.setId(id);
	            StudentDTO updatedStudent = studentService.save(studentDTO);
	            return ResponseEntity.ok(updatedStudent);
	        } catch (StudentException e) {
	            return  new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	        } catch (Exception e) {
	            return  new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	    @DeleteMapping("/delete/{id}")
	    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
	        try {
	            studentService.deleteById(id);
	            return new ResponseEntity<>(HttpStatus.OK);
	        } catch (StudentException e) {
	            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	        } catch (Exception e) {
	            return new ResponseEntity<>("An error occurred while deleting the student.", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
}
