package com.giannijin.ITS.Prodigi.v3.controller;

import com.giannijin.ITS.Prodigi.v3.model.Student;
import com.giannijin.ITS.Prodigi.v3.model.Teacher;
import com.giannijin.ITS.Prodigi.v3.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @GetMapping
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @GetMapping("/{teacherId}")
    public Teacher getTeachersById(@PathVariable Long teacherId) {
        return teacherService.getTeacherById(teacherId);
    }

    @DeleteMapping("/{teacherId}")
    public void deleteTeacherById(@PathVariable Long teacherId) {
        teacherService.deleteTeacherById(teacherId);
    }
    @GetMapping("/subject")
    public List<Teacher> findTeachersBySubject(@RequestParam String subjectName) {
        return teacherService.findTeachersBySubject(subjectName);
    }
    @PostMapping
    public Teacher saveTeacherDetails(@Validated @RequestBody Teacher teacher) {
        return teacherService.saveTeacherDetails(teacher);
    }

    @PutMapping("/{teacherId}")
    public Teacher updateTeacherDetails(@RequestBody Teacher teacher, @PathVariable Long teacherId) {
        return teacherService.updateTeacherDetails(teacherId, teacher);
    }

    @GetMapping("/{teacherId}/students")
    public List<Student> findAllStudentsByProgramId(@PathVariable Long teacherId) {
        return teacherService.findAllStudentsByProgramId(teacherId);
    }

    @GetMapping("/students/name")
    public List<Student> findByFirstNameOrLastName(@RequestParam String firstName, @RequestParam String lastName) {
        return teacherService.findByFirstNameOrLastName(firstName, lastName);
    }

    @GetMapping("/students/program/{program}")
    public List<Student> findByProgram(@PathVariable String program) {
        return teacherService.findByProgram(program);
    }

    @GetMapping("/students/total")
    public Integer findTotalStudents() {
        return teacherService.findTotalStudents();
    }

    @GetMapping("/students/year")
    public List<Student> findByStartYearAndEndYear(@RequestParam Integer startYear, @RequestParam Integer endYear) {
        return teacherService.findByStartYearAndEndYear(startYear, endYear);
    }

    @GetMapping("/students/year/{year}")
    public List<Student> findByStartYearOrEndYear(@PathVariable Integer year) {
        return teacherService.findByStartYearOrEndYear(year);
    }

}