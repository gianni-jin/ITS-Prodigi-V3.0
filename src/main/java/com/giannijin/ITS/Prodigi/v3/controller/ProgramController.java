package com.giannijin.ITS.Prodigi.v3.controller;

import com.giannijin.ITS.Prodigi.v3.model.Program;
import com.giannijin.ITS.Prodigi.v3.model.Student;
import com.giannijin.ITS.Prodigi.v3.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/programs")
public class ProgramController {

    @Autowired
    private ProgramService programService;

    @PostMapping
    public Program saveProgram(@RequestBody Program program) {
        return programService.saveProgram(program);
    }

    @GetMapping
    public List<Program> getAllPrograms() {
        return programService.getAllPrograms();
    }

    @GetMapping("/{id}")
    public Program getProgramById(@PathVariable Long id) {
        return programService.getProgramById(id);
    }

    @PutMapping("/{id}")
    public Program updateProgram(@PathVariable Long id, @RequestBody Program program) {
        program.setProgramId(id);
        return programService.updateProgram(program);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgram(@PathVariable Long id) {
        programService.deleteProgram(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/name")
    public List<Program> findByName(@RequestParam String name) {
        return programService.findByName(name);
    }

    @GetMapping("/location")
    public List<Program> findByLocation(@RequestParam String location) {
        return programService.findByLocation(location);
    }

    @GetMapping("/total")
    public Integer findTotalPrograms() {
        return programService.findTotalPrograms();
    }


    @GetMapping("/description")
    public List<Program> findByDescription(@RequestParam String description) {
        return programService.findByDescription(description);
    }

    @GetMapping("/location/total")
    public List<Object[]> findTotalProgramsForEachLocation() {
        return programService.findTotalProgramsForEachLocation();
    }


    @GetMapping("/{id}/students")
    public List<Student> findAllStudentsByProgramId(@PathVariable Long id) {
        return programService.findAllStudentsByProgramId(id);
    }
}