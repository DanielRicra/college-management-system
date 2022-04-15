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
        boolean existingCourse = courseRepository.findById(course.getCourseId()).isPresent();

        if (existingCourse) {
            return Optional.empty();
        }

        return Optional.of(courseRepository.save(course));
    }

    public Optional<Course> updateCourse(String courseId, Course course) {
        int isUpdated = courseRepository.updateCourse(course.getName(),
                course.getCredits(),
                course.getDescription(),
                courseId);
        if (isUpdated > 0) {
            course.setCourseId(courseId);
            return Optional.of(course);
        }
        return Optional.empty();
    }

    public boolean deleteCourseById(String courseId) {
        boolean existingCourse = courseRepository.findById(courseId).isPresent();
        if (existingCourse) {
            courseRepository.deleteById(courseId);
            return true;
        }
        return false;
    }

    public Optional<Course> getCourseById(String courseId) {
        return courseRepository.findById(courseId);
    }
}
