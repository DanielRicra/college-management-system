package com.collegeapp.management.repository;

import com.collegeapp.management.entity.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @Test
    void itShouldCheckIfStudentEmailExist() {
        // give
        String email = "daniel@test.com";
        Student student = new Student(
                "1234567",
                "Daniel",
                "Ricra",
                "7894561",
                email,
                "78945612",
                "Av. Lexinton",
                LocalDate.of(2000, Month.MARCH, 12));
        underTest.save(student);

        // when
        boolean expected = underTest.findStudentByEmail(email).isPresent();

        // then
        assertThat(expected).isTrue();
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }
}