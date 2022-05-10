package com.collegeapp.management.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "exams")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exam_id", nullable = false)
    private Integer examId;

    @Column(name = "starts_at")
    private LocalDateTime startsAt;

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false, updatable = false)
    private Course course;

    public Exam() {
    }

    public Exam(Integer examId,
                LocalDateTime startsAt,
                LocalDateTime expiresAt,
                String description,
                Course course) {
        this.examId = examId;
        this.startsAt = startsAt;
        this.expiresAt = expiresAt;
        this.description = description;
        this.course = course;
    }

    public Exam(LocalDateTime startsAt, LocalDateTime expiresAt, String description, Course course) {
        this.startsAt = startsAt;
        this.expiresAt = expiresAt;
        this.description = description;
        this.course = course;
    }

    public Integer getId() {
        return examId;
    }

    public void setId(Integer id) {
        this.examId = examId;
    }

    public LocalDateTime getStartsAt() {
        return startsAt;
    }

    public void setStartsAt(LocalDateTime startsAt) {
        this.startsAt = startsAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "examId=" + examId +
                ", startsAt=" + startsAt +
                ", expiresAt=" + expiresAt +
                ", description='" + description + '\'' +
                ", course=" + course +
                '}';
    }
}