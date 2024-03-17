package com.giannijin.ITS.Prodigi.v3.service;

import com.giannijin.ITS.Prodigi.v3.model.Program;
import com.giannijin.ITS.Prodigi.v3.model.Student;

import java.util.List;

public interface ProgramService {
    Program saveProgram(Program program);

    List<Program> getAllPrograms();

    Program getProgramById(Long id);

    Program updateProgram(Program program);

    void deleteProgram(Long id);

    List<Program> findByName(String name);

    List<Program> findByLocation(String location);

    Integer findTotalPrograms();


    List<Program> findByDescription(String description);

    List<Object[]> findTotalProgramsForEachLocation();
    List<Student> findAllStudentsByProgramId(Long programId);

}

