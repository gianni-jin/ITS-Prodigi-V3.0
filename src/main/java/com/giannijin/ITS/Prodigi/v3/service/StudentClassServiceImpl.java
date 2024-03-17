package com.giannijin.ITS.Prodigi.v3.service;

import com.giannijin.ITS.Prodigi.v3.model.Program;
import com.giannijin.ITS.Prodigi.v3.model.Student;
import com.giannijin.ITS.Prodigi.v3.model.StudentClass;
import com.giannijin.ITS.Prodigi.v3.exception.ResourceNotFoundException;
import com.giannijin.ITS.Prodigi.v3.repository.StudentClassRepository;
import com.giannijin.ITS.Prodigi.v3.repository.StudentRepository;
import com.giannijin.ITS.Prodigi.v3.repository.ProgramRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class StudentClassServiceImpl implements StudentClassService {
    @Autowired
    private StudentClassRepository studentClassRepository;


    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ProgramRepository programRepository;
    @Override
    public List<StudentClass> getAllStudentClasses() {
        return studentClassRepository.findAll();
    }

    @Override
    public StudentClass saveStudentClassDetails(StudentClass studentClass) {
        if (studentClass.getProgram() != null) {
            if (studentClass.getProgram().getProgramId() != null) {
                Optional<Program> existingProgram = programRepository.findById(studentClass.getProgram().getProgramId());
                if (existingProgram.isPresent()) {
                    Program programToUpdate = existingProgram.get();
                    programToUpdate.setName(studentClass.getProgram().getName());
                    programToUpdate.setAddress(studentClass.getProgram().getAddress());
                    programToUpdate.setLocation(studentClass.getProgram().getLocation());
                    programToUpdate.setDescription(studentClass.getProgram().getDescription());
                    programToUpdate.setCode(studentClass.getProgram().getCode());
                    programToUpdate.setDate(studentClass.getProgram().getDate());
                    studentClass.setProgram(programRepository.save(programToUpdate));
                } else {
                    studentClass.setProgram(programRepository.save(studentClass.getProgram()));
                }
            } else {
                studentClass.setProgram(programRepository.save(studentClass.getProgram()));
            }
        }

        List<Student> students = new ArrayList<>();
        for (Student student : studentClass.getStudentList()) {
            if (student.getStudentId() != null) {
                Optional<Student> existingStudent = studentRepository.findById(student.getStudentId());
                if (existingStudent.isPresent()) {
                    Student studentToUpdate = getStudentToUpdate(student, existingStudent);
                    students.add(studentRepository.save(studentToUpdate));
                } else {
                    students.add(studentRepository.save(student));
                }
            } else {
                students.add(studentRepository.save(student));
            }
        }
        studentClass.setStudentList(students);

        return studentClassRepository.save(studentClass);
    }
    @Override
    public StudentClass getStudentClassById(Long StudentClassId) {
        return studentClassRepository.findById(StudentClassId)
                .orElseThrow(() -> new ResourceNotFoundException("StudentClass not found for the id " + StudentClassId));
    }

    @Override
    public StudentClass updateStudentClassDetails(Long id, StudentClass studentClass) {
        StudentClass existingStudentClass = getStudentClassById(id);

        List<Student> students = new ArrayList<>();
        for (Student student : studentClass.getStudentList()) {
            if (student.getStudentId() != null) {
                Optional<Student> existingStudent = studentRepository.findById(student.getStudentId());
                if (existingStudent.isPresent()) {
                    Student studentToUpdate = getStudentToUpdate(student, existingStudent);
                    students.add(studentRepository.save(studentToUpdate));
                } else {
                    students.add(studentRepository.save(student));
                }
            } else {
                students.add(studentRepository.save(student));
            }
        }
        existingStudentClass.setStudentList(students);
        return studentClassRepository.save(existingStudentClass);
    }

    private static Student getStudentToUpdate(Student student, Optional<Student> existingStudent) {
        Student studentToUpdate = existingStudent.get();
        studentToUpdate.setFirstName(student.getFirstName());
        studentToUpdate.setLastName(student.getLastName());
        studentToUpdate.setBirthDate(student.getBirthDate());
        studentToUpdate.setGender(student.getGender());
        studentToUpdate.setStartYear(student.getStartYear());
        studentToUpdate.setEndYear(student.getEndYear());
        studentToUpdate.setEmail(student.getEmail());
        studentToUpdate.setResidenceAddress(student.getResidenceAddress());
        return studentToUpdate;

    }

    @Override
    public void deleteStudentClassById(Long id) {
        if (!studentClassRepository.existsById(id)) {
            throw new ResourceNotFoundException("StudentClass not found for the id " + id);
        }
        studentClassRepository.deleteById(id);
    }

    @Override
    public List<StudentClass> findByStartYearOrEndYear(Integer startYear, Integer endYear) {
        return studentClassRepository.findByStartYearOrEndYear(startYear, endYear);
    }

    @Override
    public List<StudentClass> findByDateBetween(Date startDate, Date endDate) {
        return studentClassRepository.findByDateBetween(startDate, endDate);
    }

    @Override
    public List<StudentClass> findByName(String name) {
        return studentClassRepository.findByName(name);
    }

    @Override
    public List<StudentClass> findByStartYearAndEndYear(Integer startYear, Integer endYear) {
        return studentClassRepository.findByStartYearAndEndYear(startYear, endYear);
    }
}