package com.giannijin.ITS.Prodigi.v3.service;

import com.giannijin.ITS.Prodigi.v3.model.Student;
import com.giannijin.ITS.Prodigi.v3.model.StudentClass;
import com.giannijin.ITS.Prodigi.v3.exception.ResourceNotFoundException;
import com.giannijin.ITS.Prodigi.v3.repository.StudentClassRepository;
import com.giannijin.ITS.Prodigi.v3.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentClassRepository studentClassRepository;

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student saveStudentDetails(Student student) {
        if (student.getStudentClass() != null) {
            if (student.getStudentClass().getStudentClassId() != null) {
                Optional<StudentClass> existingStudentClass = studentClassRepository.findById(student.getStudentClass().getStudentClassId());
                if (existingStudentClass.isPresent()) {
                    StudentClass studentClassToUpdate = existingStudentClass.get();
                    studentClassToUpdate.setName(student.getStudentClass().getName());
                    student.setStudentClass(studentClassRepository.save(studentClassToUpdate));
                } else {
                    student.setStudentClass(studentClassRepository.save(student.getStudentClass()));
                }
            } else {
                student.setStudentClass(studentClassRepository.save(student.getStudentClass()));
            }
        }
        return studentRepository.save(student);
    }
    @Override
    public Student updateStudentDetails(Long studentId, Student student) {
        Student existingStudent = getStudentsById(studentId);

        if (student.getStudentClass() != null) {
            if (student.getStudentClass().getStudentClassId() != null) {
                Optional<StudentClass> existingStudentClass = studentClassRepository.findById(student.getStudentClass().getStudentClassId());
                if (existingStudentClass.isPresent()) {
                    StudentClass studentClassToUpdate = existingStudentClass.get();
                    studentClassToUpdate.setName(student.getStudentClass().getName());
                    existingStudent.setStudentClass(studentClassRepository.save(studentClassToUpdate));
                } else {
                    existingStudent.setStudentClass(studentClassRepository.save(student.getStudentClass()));
                }
            } else {
                existingStudent.setStudentClass(studentClassRepository.save(student.getStudentClass()));
            }
        }

        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setResidenceAddress(student.getResidenceAddress());
        existingStudent.setBirthDate(student.getBirthDate());
        existingStudent.setPaymentStatus(student.getPaymentStatus());
        existingStudent.setProgram(student.getProgram());
        existingStudent.setStartYear(student.getStartYear());
        existingStudent.setEndYear(student.getEndYear());
        existingStudent.setDate(student.getDate());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setGender(student.getGender());

        return studentRepository.save(existingStudent);
    }
    @Override
    public void deleteStudentById(Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new ResourceNotFoundException("Student not found for the id " + studentId);
        }
        studentRepository.deleteById(studentId);
    }

    @Override
    public Student getStudentsById(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for the id " + studentId));
    }

    @Override
    public List<Student> findByFirstNameOrLastName(String firstName, String lastName) {
        return studentRepository.findByFirstNameOrLastName(firstName, lastName);
    }

    @Override
    public List<Student> findByProgram(String program) {
        return studentRepository.findByProgram(program);
    }

    @Override
    public Integer findTotalStudents() {
        return studentRepository.findTotalStudents();
    }

    @Override
    public List<Student> findByStartYearAndEndYear(Integer startYear, Integer endYear) {
        return studentRepository.findByStartYearAndEndYear(startYear, endYear);
    }

    @Override
    public List<Object[]> findTotalStudentsForEachProgram() {
        return studentRepository.findTotalStudentsForEachProgram();
    }
}