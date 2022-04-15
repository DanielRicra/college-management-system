package com.collegeapp.management.repository;

import com.collegeapp.management.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, String> {

    @Query("SELECT p FROM Professor p WHERE p.email=?1")
    Optional<Professor> findProfessorByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE Professor p SET p.fullName=?1, p.dni=?2, p.address=?3, p.email=?4, p.telephone=?5, p.dob=?6 WHERE p.id=?7")
    int updateProfessor(String fullName,
                        String dni,
                        String address,
                        String email,
                        String telephone,
                        LocalDate dob,
                        String professorId);

    @Transactional
    @Modifying
    @Query("UPDATE Professor p SET p.fullName=?1, p.dni=?2, p.address=?3, p.telephone=?4, p.dob=?5 WHERE p.id=?6")
    int updateProfessorDetails(String fullName,
                        String dni,
                        String address,
                        String telephone,
                        LocalDate dob,
                        String professorId);
}
