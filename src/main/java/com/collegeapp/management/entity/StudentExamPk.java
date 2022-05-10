package com.collegeapp.management.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class StudentExamPk implements Serializable {

    @Column(name = "student_id", columnDefinition = "CHAR(7)")
    private String studentId;

    @Column(name = "exam_id")
    private Integer examId;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }
}
