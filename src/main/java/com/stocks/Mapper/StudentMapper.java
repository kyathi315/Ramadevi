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
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
        dto.setRollnumber(student.getRollNumber());
        dto.setPhoneNumber(student.getPhoneNumber());
        return dto;
    }

    public static Student toEntity(StudentDTO dto) {
        if (dto == null) {
            return null;
        }
        Student student = new Student();
        student.setId(dto.getId()); 
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setRollNumber(dto.getRollnumber());
        student.setPhoneNumber(dto.getPhoneNumber());
        return student;
    }

}
