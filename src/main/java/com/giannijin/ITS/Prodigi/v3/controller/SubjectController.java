package com.giannijin.ITS.Prodigi.v3.controller;

import com.giannijin.ITS.Prodigi.v3.model.Subject;
import com.giannijin.ITS.Prodigi.v3.model.Teacher;
import com.giannijin.ITS.Prodigi.v3.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @GetMapping
    public List<Subject> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    @GetMapping("/{id}")
    public Subject getSubjectsById(@PathVariable Long id) {
        return subjectService.getSubjectsById(id);
    }

    @DeleteMapping("/{subjectId}")
    public void deleteSubjectById(@PathVariable Long subjectId) {
        subjectService.deleteSubjectById(subjectId);
    }

    @PostMapping
    public Subject saveSubjectDetails(@Validated @RequestBody Subject subject) {
        return subjectService.saveSubjectDetails(subject);
    }

    @PutMapping("/{id}")
    public Subject updateSubjectDetails(@RequestBody Subject subject, @PathVariable Long id) {
        return subjectService.updateSubjectDetails(id, subject);
    }
    @GetMapping("/teachers/{subjectName}")
    public List<Teacher> findTeachersBySubject(@PathVariable String subjectName) {
        return subjectService.findTeachersBySubject(subjectName);
    }

    @GetMapping("/program/{program}")
    public List<Subject> findByProgram(@PathVariable String program) {
        return subjectService.findByProgram(program);
    }

    @GetMapping("/startYear/{startYear}")
    public List<Subject> findByStartYear(@PathVariable Integer startYear) {
        return subjectService.findByStartYear(startYear);
    }

    @GetMapping("/program/count")
    public List<Object[]> findTotalSubjectsForEachProgram() {
        return subjectService.findTotalSubjectsForEachProgram();
    }

}