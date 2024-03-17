package com.giannijin.ITS.Prodigi.v3.service;

import com.giannijin.ITS.Prodigi.v3.model.Subject;
import com.giannijin.ITS.Prodigi.v3.model.Teacher;
import jakarta.transaction.Transactional;

import java.util.List;

@Transactional
public interface SubjectService {

    List<Subject> getAllSubjects();

    Subject saveSubjectDetails(Subject subject);

    Subject updateSubjectDetails(Long subjectId, Subject subject);

    void deleteSubjectById(Long subjectId);

    Subject getSubjectsById(Long subjectId);

    List<Teacher> findTeachersBySubject(String subjectName);

    List<Subject> findByProgram(String program);

    List<Subject> findByStartYear(Integer startYear);

    List<Object[]> findTotalSubjectsForEachProgram();
}