package com.collegeapp.management.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @Column(name = "course_id", nullable = false, updatable = false, columnDefinition = "CHAR(9)")
    private String courseId;

    @Column(name = "name", length = 80, nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "text", nullable = false)
    private String description;

    @Column(name = "credits", columnDefinition = "NUMERIC(5)")
    private Integer credits;

    @OneToMany(mappedBy = "course", cascade = {CascadeType.ALL})
    private List<Classroom> classrooms;

    public Course(String id, String name, String description, Integer credits) {
        this.courseId = id;
        this.name = name;
        this.description = description;
        this.credits = credits;
    }

    public Course() {
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public List<Classroom> getClassroom() {
        return classrooms;
    }

    public void setClassroom(List<Classroom> classrooms) {
        this.classrooms = classrooms;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + courseId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", credits=" + credits +
                '}';
    }
}
