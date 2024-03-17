package com.giannijin.ITS.Prodigi.v3.service;

import com.giannijin.ITS.Prodigi.v3.model.Student;
import com.giannijin.ITS.Prodigi.v3.model.Teacher;
import com.giannijin.ITS.Prodigi.v3.exception.ResourceNotFoundException;
import com.giannijin.ITS.Prodigi.v3.repository.SubjectRepository;
import com.giannijin.ITS.Prodigi.v3.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher saveTeacherDetails(Teacher teacher) {
        return teacherRepository.save(teacher);
    }
    @Override
    public List<Teacher> findTeachersBySubject(String subjectName) {
        return subjectRepository.findTeachersBySubject(subjectName);
    }

    @Override
    public Teacher updateTeacherDetails(Long teacherId, Teacher teacherDetails) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found for this id :: " + teacherId));

        teacher.setFirstName(teacherDetails.getFirstName());
        teacher.setLastName(teacherDetails.getLastName());
        teacher.setModule(teacherDetails.getModule());
        teacher.setResidenceAddress(teacherDetails.getResidenceAddress());
        teacher.setGender(teacherDetails.getGender());
        teacher.setBirthDate(teacherDetails.getBirthDate());
        teacher.setSalary(teacherDetails.getSalary());
        teacher.setPhoneNumber(teacherDetails.getPhoneNumber());
        teacher.setEmail(teacherDetails.getEmail());
        teacher.setStartYear(teacherDetails.getStartYear());
        teacher.setEndYear(teacherDetails.getEndYear());
        teacher.setDate(teacherDetails.getDate());
        teacher.setSubjects(teacherDetails.getSubjects());
        teacher.setStudentClasses(teacherDetails.getStudentClasses());

        final Teacher updatedTeacher = teacherRepository.save(teacher);
        return updatedTeacher;
    }

    @Override
    public void deleteTeacherById(Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found for this id :: " + teacherId));
        teacherRepository.delete(teacher);
    }

    @Override
    public Teacher getTeacherById(Long teacherId) {
        return teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found for this id :: " + teacherId));
    }

    @Override
    public List<Student> findAllStudentsByProgramId(Long programId) {
        return teacherRepository.findByProgram(programId.toString());
    }

    @Override
    public List<Student> findByFirstNameOrLastName(String firstName, String lastName) {
        return teacherRepository.findByFirstNameOrLastName(firstName, lastName);
    }

    @Override
    public List<Student> findByProgram(String program) {
        return teacherRepository.findByProgram(program);
    }

    @Override
    public Integer findTotalStudents() {
        return teacherRepository.findTotalStudents();
    }

    @Override
    public List<Student> findByStartYearAndEndYear(Integer startYear, Integer endYear) {
        return teacherRepository.findByStartYearOrEndYear(startYear);
    }

    @Override
    public List<Student> findByStartYearOrEndYear(Integer year) {
        return teacherRepository.findByStartYearOrEndYear(year);
    }
}