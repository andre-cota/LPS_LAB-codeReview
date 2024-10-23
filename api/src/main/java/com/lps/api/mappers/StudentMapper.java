package com.lps.api.mappers;

import com.lps.api.dtos.StudentRegisterDto;
import com.lps.api.models.Course;
import com.lps.api.models.Student;

public class StudentMapper {

    public static Student toStudent(StudentRegisterDto studentRegisterDto, Course course) {
        return new Student(
                studentRegisterDto.name(),
                studentRegisterDto.email(),
                studentRegisterDto.password(),
                studentRegisterDto.cpf(),
                studentRegisterDto.balance(),
                studentRegisterDto.rg(),
                studentRegisterDto.address(),
                course);
    }

}
