package com.collegeapp.management.repository;

import com.collegeapp.management.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CourseRepository extends JpaRepository<Course, String> {

    @Transactional
    @Modifying
    @Query("UPDATE Course c SET c.name=?1, c.credits=?2, c.description=?3 WHERE c.courseId=?4")
    int updateCourse(String name, Integer credits, String description, String courseId);
}
