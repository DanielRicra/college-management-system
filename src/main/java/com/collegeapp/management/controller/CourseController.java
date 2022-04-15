package com.collegeapp.management.controller;

import com.collegeapp.management.entity.Course;
import com.collegeapp.management.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/course")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<Course>> getCourses() {
        return new ResponseEntity<>(courseService.getCourses(), HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable("id") String courseId) {
        return courseService.getCourseById(courseId)
                .map(course -> new ResponseEntity<>(course, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Course> saveCourse(@RequestBody Course course) {
        return courseService.saveCourse(course)
                .map(savedCourse -> new ResponseEntity<>(savedCourse, HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(HttpStatus.CONFLICT));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable("id") String courseId,
                                               @RequestBody Course course) {
        return courseService.updateCourse(courseId, course)
                .map(updatedCourse -> new ResponseEntity<>(updatedCourse, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.CONFLICT));
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity deleteCourseById(@PathVariable("id") String courseId) {
        if (courseService.deleteCourseById(courseId)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}
