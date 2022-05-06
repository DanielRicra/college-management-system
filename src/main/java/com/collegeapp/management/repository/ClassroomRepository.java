package com.collegeapp.management.repository;

import com.collegeapp.management.entity.Classroom;
import com.collegeapp.management.entity.projections.ClassroomSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClassroomRepository extends JpaRepository<Classroom, Integer> {

    @Query("select c from Classroom c")
    List<ClassroomSummary> findAllClassrooms();

    @Query("select c from Classroom c where c.id = ?1")
    Optional<ClassroomSummary> findClassroomById(Integer classroomId);
}
