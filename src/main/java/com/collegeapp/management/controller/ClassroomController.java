package com.collegeapp.management.controller;

import com.collegeapp.management.entity.Classroom;
import com.collegeapp.management.entity.projections.ClassroomSummary;
import com.collegeapp.management.entity.Course;
import com.collegeapp.management.entity.Professor;
import com.collegeapp.management.repository.CourseRepository;
import com.collegeapp.management.repository.ProfessorRepository;
import com.collegeapp.management.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/classroom")
public class ClassroomController {

    private final ClassroomService classroomService;

    private final ProfessorRepository professorRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public ClassroomController(ClassroomService classroomService,
                               ProfessorRepository professorRepository,
                               CourseRepository courseRepository) {
        this.classroomService = classroomService;
        this.professorRepository = professorRepository;
        this.courseRepository = courseRepository;
    }

    @GetMapping
    public ResponseEntity<List<ClassroomSummary>> getClassrooms() {
        return  new ResponseEntity<>(classroomService.getClassrooms(), HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<ClassroomSummary> getClassroomById(@PathVariable("id") Integer classroomId) {
        return classroomService.getClassroomById(classroomId)
                .map(classroom -> new ResponseEntity<>(classroom, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    public ResponseEntity<Classroom> saveClassroom(@RequestBody Classroom classroom,
                                        @RequestParam(name = "course") String courseId,
                                        @RequestParam(name = "professor") String professorId) {

        Optional<Professor> professor = professorRepository.findById(professorId);
        Optional<Course> course = courseRepository.findById(courseId);

        if (professor.isPresent() && course.isPresent()) {
            classroom.setProfessor(professor.get());
            classroom.setCourse(course.get());
            return classroomService.saveClassroom(classroom)
                    .map(savedClassroom -> new ResponseEntity<>(savedClassroom, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity deleteClassroomById(@PathVariable("id") Integer classroomId) {
        if (classroomService.deleteClassroomById(classroomId)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
