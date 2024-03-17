package com.giannijin.ITS.Prodigi.v3.controller;

import com.giannijin.ITS.Prodigi.v3.model.StudentClass;
import com.giannijin.ITS.Prodigi.v3.service.StudentClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/studentClasses")
public class StudentClassController {

    @Autowired
    StudentClassService studentClassService;

    @GetMapping
    public List<StudentClass> getAllStudentClasses() {
        return studentClassService.getAllStudentClasses();
    }

    @PostMapping
    public StudentClass saveStudentClassDetails(@RequestBody StudentClass studentClass) {
        return studentClassService.saveStudentClassDetails(studentClass);
    }

    @GetMapping("/{id}")
    public StudentClass getStudentClassById(@PathVariable Long id) {
        return studentClassService.getStudentClassById(id);
    }

    @PutMapping("/{id}")
    public StudentClass updateStudentClassDetails(@PathVariable Long id, @RequestBody StudentClass studentClass) {
        return studentClassService.updateStudentClassDetails(id, studentClass);
    }

    @DeleteMapping("/{id}")
    public void deleteStudentClassById(@PathVariable Long id) {
        studentClassService.deleteStudentClassById(id);
    }

    @GetMapping("/year")
    public List<StudentClass> findByStartYearOrEndYear(@RequestParam Integer startYear, @RequestParam Integer endYear) {
        return studentClassService.findByStartYearOrEndYear(startYear, endYear);
    }

    @GetMapping("/date")
    public List<StudentClass> findByDateBetween(@RequestParam Date startDate, @RequestParam Date endDate) {
        return studentClassService.findByDateBetween(startDate, endDate);
    }

    @GetMapping("/name/{name}")
    public List<StudentClass> findByName(@PathVariable String name) {
        return studentClassService.findByName(name);
    }

    @GetMapping("/yearRange")
    public List<StudentClass> findByStartYearAndEndYear(@RequestParam Integer startYear, @RequestParam Integer endYear) {
        return studentClassService.findByStartYearAndEndYear(startYear, endYear);
    }
}