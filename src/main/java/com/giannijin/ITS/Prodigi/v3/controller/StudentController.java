package com.giannijin.ITS.Prodigi.v3.controller;

import com.giannijin.ITS.Prodigi.v3.model.Student;
import com.giannijin.ITS.Prodigi.v3.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping
    public Student saveStudentDetails(@RequestBody Student student) {
        return studentService.saveStudentDetails(student);
    }

    @PutMapping("/{id}")
    public Student updateStudentDetails(@PathVariable Long id, @RequestBody Student student) {
        return studentService.updateStudentDetails(id, student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public Student getStudentsById(@PathVariable Long id) {
        return studentService.getStudentsById(id);
    }

    @GetMapping("/name")
    public List<Student> findByFirstNameOrLastName(@RequestParam String firstName, @RequestParam String lastName) {
        return studentService.findByFirstNameOrLastName(firstName, lastName);
    }

    @GetMapping("/program/{program}")
    public List<Student> findByProgram(@PathVariable String program) {
        return studentService.findByProgram(program);
    }

    @GetMapping("/total")
    public Integer findTotalStudents() {
        return studentService.findTotalStudents();
    }

    @GetMapping("/year")
    public List<Student> findByStartYearAndEndYear(@RequestParam Integer startYear, @RequestParam Integer endYear) {
        return studentService.findByStartYearAndEndYear(startYear, endYear);
    }

    @GetMapping("/program/count")
    public List<Object[]> findTotalStudentsForEachProgram() {
        return studentService.findTotalStudentsForEachProgram();
    }
}