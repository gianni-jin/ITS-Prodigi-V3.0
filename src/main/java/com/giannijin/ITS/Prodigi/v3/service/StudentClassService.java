package com.giannijin.ITS.Prodigi.v3.service;

import com.giannijin.ITS.Prodigi.v3.model.StudentClass;
import jakarta.transaction.Transactional;

import java.util.Date;
import java.util.List;

@Transactional

public interface StudentClassService {
    List<StudentClass> getAllStudentClasses();

    StudentClass saveStudentClassDetails(StudentClass studentClass);

    StudentClass getStudentClassById(Long StudentClassId);

    void deleteStudentClassById(Long id);
    StudentClass updateStudentClassDetails(Long id, StudentClass studentClass);

    List<StudentClass> findByStartYearOrEndYear(Integer startYear, Integer endYear);

    List<StudentClass> findByDateBetween(Date startDate, Date endDate);

    List<StudentClass> findByName(String name);

    List<StudentClass> findByStartYearAndEndYear(Integer startYear, Integer endYear);

}