package com.collegeapp.management.entity.projections;

import java.time.LocalDate;

public interface ClassroomSummary {
    public Integer getId();
    public LocalDate getStarts();
    public LocalDate getEnds();
    public Integer getStudentsNumber();
    public CourseSummary getCourse();
    public ProfessorSummary getProfessor();

    interface CourseSummary {
        String getCourseId();
        String getName();
        String getDescription();
    }

    interface ProfessorSummary {
        String getId();
        String getFullName();
    }

}
