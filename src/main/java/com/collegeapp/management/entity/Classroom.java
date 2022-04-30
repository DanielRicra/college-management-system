package com.collegeapp.management.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "classrooms")
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
    @JoinColumn(name = "course_id", updatable = false, nullable = false)
    private Course course;

    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false, updatable = false)
    private Professor professor;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "student_classroom",
            joinColumns = @JoinColumn(name = "classroom_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students;

    public Classroom() {
    }

    public Classroom(LocalDate starts, LocalDate ends, Course course, Professor professor) {
        this.starts = starts;
        this.ends = ends;
        this.course = course;
        this.professor = professor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getStarts() {
        return starts;
    }

    public void setStarts(LocalDate starts) {
        this.starts = starts;
    }

    public LocalDate getEnds() {
        return ends;
    }

    public void setEnds(LocalDate ends) {
        this.ends = ends;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getCourseName(){
        if (this.course != null && this.course.getName() != null) {
            return this.course.getName();
        }
        else return "";
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public String getProfessorName(){
        if (this.professor != null && this.professor.getFullName() != null) {
            return this.professor.getFullName();
        }
        else return "";
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Classroom{" +
                "id=" + id +
                ", starts=" + starts +
                ", ends=" + ends +
                ", course=" + course +
                ", professor=" + professor +
                '}';
    }
}
