package com.collegeapp.management.entity;

import javax.persistence.*;

@Entity
@Table(name = "student_exams")
public class StudentExam {

    @EmbeddedId
    private StudentExamPk id;

    @Column(name = "note")
    private Double note;

    @ManyToOne
    @MapsId("examId")
    @JoinColumn(name = "exam_id", insertable = false, updatable = false)
    private Exam examId;

    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private Student studentId;

    public StudentExamPk getId() {
        return id;
    }

    public void setId(StudentExamPk id) {
        this.id = id;
    }

    public Double getNote() {
        return note;
    }

    public void setNote(Double note) {
        this.note = note;
    }

    public Exam getExamId() {
        return examId;
    }

    public void setExamId(Exam examId) {
        this.examId = examId;
    }

    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
    }
}
