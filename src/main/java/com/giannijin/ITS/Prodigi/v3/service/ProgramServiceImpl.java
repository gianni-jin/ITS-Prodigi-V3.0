package com.giannijin.ITS.Prodigi.v3.service;

import com.giannijin.ITS.Prodigi.v3.model.Program;
import com.giannijin.ITS.Prodigi.v3.model.Student;
import com.giannijin.ITS.Prodigi.v3.model.StudentClass;
import com.giannijin.ITS.Prodigi.v3.model.Subject;
import com.giannijin.ITS.Prodigi.v3.exception.ResourceNotFoundException;
import com.giannijin.ITS.Prodigi.v3.repository.ProgramRepository;
import com.giannijin.ITS.Prodigi.v3.repository.StudentClassRepository;
import com.giannijin.ITS.Prodigi.v3.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProgramServiceImpl implements ProgramService {

    @Autowired
    private  ProgramRepository programRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private StudentClassRepository studentClassRepository;


    @Autowired
    public ProgramServiceImpl(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    @Override
    public List<Student> findAllStudentsByProgramId(Long programId) {
        return programRepository.findAllStudentsByProgramId(programId);
    }
    @Override
    public Program saveProgram(Program program) {
        if (program.getStudentClasses() != null) {
            List<StudentClass> studentClasses = new ArrayList<>();
            for (StudentClass studentClass : program.getStudentClasses()) {
                if (studentClass.getStudentClassId() != null) {
                    Optional<StudentClass> existingStudentClass = studentClassRepository.findById(studentClass.getStudentClassId());
                    if (existingStudentClass.isPresent()) {
                        StudentClass studentClassToUpdate = existingStudentClass.get();
                        studentClasses.add(studentClassRepository.save(studentClassToUpdate));
                    } else {
                        studentClasses.add(studentClassRepository.save(studentClass));
                    }
                } else {
                    studentClasses.add(studentClassRepository.save(studentClass));
                }
            }
            program.setStudentClasses(studentClasses);
        }

        if (program.getSubjects() != null) {
            List<Subject> subjects = new ArrayList<>();
            for (Subject subject : program.getSubjects()) {
                if (subject.getSubjectId() != null) {
                    Optional<Subject> existingSubject = subjectRepository.findById(subject.getSubjectId());
                    if (existingSubject.isPresent()) {
                        Subject subjectToUpdate = existingSubject.get();
                        subjects.add(subjectRepository.save(subjectToUpdate));
                    } else {
                        subjects.add(subjectRepository.save(subject));
                    }
                } else {
                    subjects.add(subjectRepository.save(subject));
                }
            }
            program.setSubjects(subjects);
        }

        // Finally, save the Program
        return programRepository.save(program);
    }

    @Override
    public List<Program> getAllPrograms() {
        return programRepository.findAll();
    }

    @Override
    public Program getProgramById(Long id) {
        return programRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Program not found with id: " + id));
    }

    @Override
    public Program updateProgram(Program program) {
        Program existingProgram = getProgramById(program.getProgramId());


        if (program.getStudentClasses() != null) {
            List<StudentClass> studentClasses = new ArrayList<>();
            for (StudentClass studentClass : program.getStudentClasses()) {
                if (studentClass.getStudentClassId() != null) {
                    Optional<StudentClass> existingStudentClass = studentClassRepository.findById(studentClass.getStudentClassId());
                    if (existingStudentClass.isPresent()) {
                        StudentClass studentClassToUpdate = existingStudentClass.get();
                        studentClasses.add(studentClassRepository.save(studentClassToUpdate));
                    } else {
                        studentClasses.add(studentClassRepository.save(studentClass));
                    }
                } else {
                    studentClasses.add(studentClassRepository.save(studentClass));
                }
            }
            existingProgram.setStudentClasses(studentClasses);
        }

        if (program.getSubjects() != null) {
            List<Subject> subjects = new ArrayList<>();
            for (Subject subject : program.getSubjects()) {
                if (subject.getSubjectId() != null) {
                    Optional<Subject> existingSubject = subjectRepository.findById(subject.getSubjectId());
                    if (existingSubject.isPresent()) {
                        Subject subjectToUpdate = existingSubject.get();
                        subjects.add(subjectRepository.save(subjectToUpdate));
                    } else {
                        subjects.add(subjectRepository.save(subject));
                    }
                } else {
                    subjects.add(subjectRepository.save(subject));
                }
            }
            existingProgram.setSubjects(subjects);
        }

        return programRepository.save(existingProgram);
    }
    @Override
    public void deleteProgram(Long id) {
        if (!programRepository.existsById(id)) {
            throw new ResourceNotFoundException("Program not found with id: " + id);
        }
        programRepository.deleteById(id);
    }

    @Override
    public List<Program> findByName(String name) {
        return programRepository.findByName(name);
    }

    @Override
    public List<Program> findByLocation(String location) {
        return programRepository.findByLocation(location);
    }


    @Override
    public Integer findTotalPrograms() {
        return programRepository.findTotalPrograms();
    }



    @Override
    public List<Program> findByDescription(String description) {
        return programRepository.findByDescription(description);
    }

    @Override
    public List<Object[]> findTotalProgramsForEachLocation() {
        return programRepository.findTotalProgramsForEachLocation();
    }
}