package com.collegeapp.management.entity.projections;

import java.time.LocalDate;

public interface ClassroomSummary {
    public Integer getId();
    public LocalDate getStarts();
    public LocalDate getEnds();
    public CourseSummary getCourse();
    public ProfessorSummary getProfessor();

    interface CourseSummary {
        String getCourseId();
        String getName();
    }

    interface ProfessorSummary {
        String getId();
        String getFullName();
    }

}
