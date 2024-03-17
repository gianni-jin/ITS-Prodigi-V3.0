package com.giannijin.ITS.Prodigi.v3.service;

import com.giannijin.ITS.Prodigi.v3.model.Subject;
import com.giannijin.ITS.Prodigi.v3.model.Teacher;
import com.giannijin.ITS.Prodigi.v3.model.Program;
import com.giannijin.ITS.Prodigi.v3.exception.ResourceNotFoundException;
import com.giannijin.ITS.Prodigi.v3.repository.SubjectRepository;
import com.giannijin.ITS.Prodigi.v3.repository.TeacherRepository;
import com.giannijin.ITS.Prodigi.v3.repository.ProgramRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
@Transactional
@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ProgramRepository programRepository;

    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject saveSubjectDetails(Subject subject) {
        if (subject.getProgram() != null) {
            if (subject.getProgram().getProgramId() != null) {
                Optional<Program> existingProgram = programRepository.findById(subject.getProgram().getProgramId());
                if (existingProgram.isPresent()) {
                    Program programToUpdate = existingProgram.get();
                    programToUpdate.setName(subject.getProgram().getName());
                    programToUpdate.setAddress(subject.getProgram().getAddress());
                    programToUpdate.setLocation(subject.getProgram().getLocation());
                    programToUpdate.setDescription(subject.getProgram().getDescription());
                    programToUpdate.setCode(subject.getProgram().getCode());
                    programToUpdate.setDate(subject.getProgram().getDate());
                    subject.setProgram(programRepository.save(programToUpdate));
                } else {
                    subject.setProgram(programRepository.save(subject.getProgram()));
                }
            } else {
                subject.setProgram(programRepository.save(subject.getProgram()));
            }
        }

        List<Teacher> teachers = new ArrayList<>();
        for (Teacher teacher : subject.getTeacherList()) {
            if (teacher.getTeacherId() != null) {
                Optional<Teacher> existingTeacher = teacherRepository.findById(teacher.getTeacherId());
                if (existingTeacher.isPresent()) {
                    Teacher teacherToUpdate = getTeacherToUpdate(teacher, existingTeacher);
                    teachers.add(teacherRepository.save(teacherToUpdate));
                } else {
                    teachers.add(teacherRepository.save(teacher));
                }
            } else {
                teachers.add(teacherRepository.save(teacher));
            }
        }
        subject.setTeacherList(teachers);

        return subjectRepository.save(subject);
    }

    @Override
    public Subject updateSubjectDetails(Long subjectId, Subject subject) {
        Subject existingSubject = getSubjectsById(subjectId);

        List<Teacher> teachers = new ArrayList<>();
        for (Teacher teacher : subject.getTeacherList()) {
            if (teacher.getTeacherId() != null) {
                Optional<Teacher> existingTeacher = teacherRepository.findById(teacher.getTeacherId());
                if (existingTeacher.isPresent()) {
                    Teacher teacherToUpdate = getTeacherToUpdate(teacher, existingTeacher);
                    teachers.add(teacherRepository.save(teacherToUpdate));
                } else {
                    teachers.add(teacherRepository.save(teacher));
                }
            } else {
                teachers.add(teacherRepository.save(teacher));
            }
        }
        existingSubject.setTeacherList(teachers);
        return subjectRepository.save(existingSubject);
    }

    private static Teacher getTeacherToUpdate(Teacher teacher, Optional<Teacher> existingTeacher) {
        Teacher teacherToUpdate = existingTeacher.get();
        teacherToUpdate.setFirstName(teacher.getFirstName());
        teacherToUpdate.setLastName(teacher.getLastName());
        teacherToUpdate.setSalary(teacher.getSalary());
        teacherToUpdate.setBirthDate(teacher.getBirthDate());
        teacherToUpdate.setModule(teacher.getModule());
        teacherToUpdate.setPhoneNumber(teacher.getPhoneNumber());
        teacherToUpdate.setGender(teacher.getGender());
        teacherToUpdate.setStartYear(teacher.getStartYear());
        teacherToUpdate.setEndYear(teacher.getEndYear());
        teacherToUpdate.setEmail(teacher.getEmail());
        teacherToUpdate.setResidenceAddress(teacher.getResidenceAddress());
        return teacherToUpdate;
    }

    @Override
    public void deleteSubjectById(Long subjectId) {
        subjectRepository.deleteById(subjectId);
    }

    @Override
    public Subject getSubjectsById(Long subjectId) {
        Optional<Subject> subject = subjectRepository.findById(subjectId);
        if (subject.isPresent()) {
            return subject.get();
        }
        throw new ResourceNotFoundException("Subject is not found for the id " + subjectId);
    }

    @Override
    public List<Teacher> findTeachersBySubject(String subjectName) {
        Subject subject = subjectRepository.findByName(subjectName)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found for this name :: " + subjectName));
        return new ArrayList<>(subject.getTeacherList());
    }

    @Override
    public List<Subject> findByProgram(String program) {
        return subjectRepository.findByProgram(program);
    }

    @Override
    public List<Subject> findByStartYear(Integer startYear) {
        return subjectRepository.findByStartYear(startYear);
    }

    @Override
    public List<Object[]> findTotalSubjectsForEachProgram() {
        return subjectRepository.findTotalSubjectsForEachProgram();
    }
}