package com.collegeapp.management.repository;

import com.collegeapp.management.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, String> {

    @Query("SELECT s FROM Student s WHERE s.email=?1")
    Optional<Student> findStudentByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE Student s SET s.firstName=?1, s.lastName=?2, s.address=?3, s.dni=?4, s.email=?5, s.dob=?6, telephone=?7 WHERE s.id=?8")
    int updateStudent(String firstName,
                                    String lastName,
                                    String address,
                                    String dni,
                                    String email,
                                    LocalDate dob,
                                    String telephone,
                                    String  id);
}
