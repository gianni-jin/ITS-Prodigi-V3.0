package com.giannijin.ITS.Prodigi.v3.service;

import com.giannijin.ITS.Prodigi.v3.model.Student;
import jakarta.transaction.Transactional;

import java.util.List;
@Transactional

public interface StudentService {

    List<Student> getAllStudents();

    Student saveStudentDetails(Student student);

    Student updateStudentDetails(Long studentId, Student student);

    void deleteStudentById(Long studentId);

    Student getStudentsById(Long studentId);

    List<Student> findByFirstNameOrLastName(String firstName, String lastName);

    List<Student> findByProgram(String program);

    Integer findTotalStudents();

    List<Student> findByStartYearAndEndYear(Integer startYear, Integer endYear);

    List<Object[]> findTotalStudentsForEachProgram();
}

