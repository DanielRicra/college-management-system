package com.collegeapp.management.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "classroom_id")
    private Integer id;

    @Column(name = "starts")
    private LocalDate starts;

    @Column(name = "ends")
    private LocalDate ends;

    @ManyToOne
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    private Course course;

    @ManyToOne
    @JoinColumn(name = "professor_id", insertable = false, updatable = false)
    private Professor professor;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
