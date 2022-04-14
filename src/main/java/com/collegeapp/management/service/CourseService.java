package com.collegeapp.management.service;


import com.collegeapp.management.entity.Course;
import com.collegeapp.management.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> saveCourse(Course course) {
        boolean existingCourse = courseRepository.findById(course.getId()).isPresent();

        if (existingCourse) {
            return Optional.empty();
        }

        return Optional.of(courseRepository.save(course));
    }
}
