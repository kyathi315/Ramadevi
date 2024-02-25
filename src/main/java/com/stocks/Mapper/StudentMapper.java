package com.stocks.Mapper;

import com.stocks.DTO.StudentDTO;
import com.stocks.user.Student;

public class StudentMapper {
	 public static StudentDTO toDTO(Student student) {
	        if (student == null) {
	            return null;
	        }
	        StudentDTO dto = new StudentDTO();
	        dto.setId(student.getId());
	        dto.setRollnumber(student.getRollNumber());
	        dto.setName(student.getName());
	        dto.setEmail(student.getEmail());
	        dto.setPhoneNumber(student.getPhoneNumber());
	        dto.setCountry(student.getCountry());
	        dto.setCity(student.getCity());
	        dto.setDateOfBirth(student.getDateOfBirth()); // Assuming this field exists in both your DTO and entity
	        // Map other fields as necessary
	        return dto;
	    }

	    // Converts a StudentDTO to a Student entity
	    public static Student toEntity(StudentDTO dto) {
	        if (dto == null) {
	            return null;
	        }
	        Student student = new Student();
	        student.setId(dto.getId()); // Be cautious with setting ID for new entities
	        student.setRollNumber(dto.getRollnumber());
	        student.setName(dto.getName());
	        student.setEmail(dto.getEmail());
	        student.setPhoneNumber(dto.getPhoneNumber());
	        student.setCountry(dto.getCountry());
	        student.setCity(dto.getCity());
	        student.setDateOfBirth(dto.getDateOfBirth()); // Assuming this field exists in both your DTO and entity
	        // Map other fields as necessary
	        return student;
	    }


}
