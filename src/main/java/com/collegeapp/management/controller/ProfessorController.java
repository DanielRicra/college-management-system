package com.collegeapp.management.controller;

import com.collegeapp.management.entity.Professor;
import com.collegeapp.management.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/professor")
public class ProfessorController {

    private final ProfessorService professorService;

    @Autowired
    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping
    public ResponseEntity<List<Professor>> getProfessors() {
        return new ResponseEntity<>(professorService.getProfessors(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Professor> saveProfessor(@RequestBody Professor professor) {
        return professorService.saveProfessor(professor)
                .map(savedProfessor -> new ResponseEntity<>(savedProfessor, HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(HttpStatus.CONFLICT));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Professor> getProfessorById(@PathVariable("id") String professorId){
        return professorService.getProfessorById(professorId)
                .map(professor -> new ResponseEntity<>(professor, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Professor> updateProfessor(@PathVariable("id") String professorId,
                                                     @RequestBody Professor professor) {
        return professorService.updateProfessor(professor, professorId)
                .map(updatedProfessor -> new ResponseEntity<>(updatedProfessor, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.CONFLICT));
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity deleteProfessorById(@PathVariable("id") String professorId) {
        if (professorService.deleteProfessorById(professorId)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
