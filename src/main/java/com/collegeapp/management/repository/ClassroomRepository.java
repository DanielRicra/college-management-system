package com.collegeapp.management.repository;

import com.collegeapp.management.entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClassroomRepository extends JpaRepository<Classroom, Integer> {

}
