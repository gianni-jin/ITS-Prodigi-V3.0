package com.giannijin.ITS.Prodigi.v3.service;

import com.giannijin.ITS.Prodigi.v3.model.Student;
import com.giannijin.ITS.Prodigi.v3.model.Teacher;
import jakarta.transaction.Transactional;

import java.util.List;

@Transactional
public interface TeacherService {
        List<Teacher> findTeachersBySubject(String subjectName);

        List<Teacher> getAllTeachers();

        Teacher saveTeacherDetails(Teacher teacher);

        Teacher updateTeacherDetails(Long teacherId, Teacher teacher);

        void deleteTeacherById(Long teacherId);

        Teacher getTeacherById(Long teacherId);
        List<Student> findAllStudentsByProgramId(Long programId);

        List<Student> findByFirstNameOrLastName(String firstName, String lastName);

        List<Student> findByProgram(String program);

        Integer findTotalStudents();

        List<Student> findByStartYearAndEndYear(Integer startYear, Integer endYear);

        List<Student> findByStartYearOrEndYear(Integer year);
}
