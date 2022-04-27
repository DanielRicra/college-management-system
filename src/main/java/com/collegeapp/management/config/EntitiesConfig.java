package com.collegeapp.management.config;

import com.collegeapp.management.entity.Course;
import com.collegeapp.management.entity.Professor;
import com.collegeapp.management.entity.Student;
import com.collegeapp.management.repository.CourseRepository;
import com.collegeapp.management.repository.ProfessorRepository;
import com.collegeapp.management.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class EntitiesConfig {

    @Bean(name = "studentConfig")
    CommandLineRunner commandLineRunner(StudentRepository studentRepository,
                                        CourseRepository courseRepository,
                                        ProfessorRepository professorRepository) {
        return args -> {
            Student daniel = new Student(
                    "0123456", "Daniel", "Ricra", "01234567", "daniel@test.com", "006549823",
                    "Av. Daniel, 254", LocalDate.of(1998, 12, 24)
            );
            Student dua = new Student(
                    "0123457", "Dua", "Lipa", "01234566", "dua@test.com", "006549823",
                    "Av. Daniel, 254", LocalDate.of(1998, 12, 24)
            );
            Student taylor = new Student(
                    "0123450", "Taylor", "Swift", "01234568", "taylor@test.com", "006549823",
                    "Av. Daniel, 254", LocalDate.of(1998, 12, 24)
            );
            studentRepository.saveAll(List.of(daniel, dua, taylor));

            Course physics = new Course(
                    "ABC_DBATH", "Physics",
                    "A physics course, were you'll have fun", 5
            );
            Course mathematics = new Course(
                    "ABC_BBATH", "Mathematics",
                    "A mathematics course, were you'll have fun", 6
            );
            Course chemistry = new Course(
                    "ABC_DBATA", "Chemistry",
                    "A chemistry course, were you'll have fun", 4
            );
            courseRepository.saveAll(List.of(physics, mathematics, chemistry));

            Professor brad = new Professor(
                    "012345", "Brad Pitt", "01234567", "brad@test.com", "006549823",
                    "Av. Daniel, 254", LocalDate.of(1998, 12, 24)
            );
            Professor selena = new Professor(
                    "012347", "Selena Gomez", "01234566", "selena@test.com", "006549823",
                    "Av. Daniel, 254", LocalDate.of(1998, 12, 24)
            );
            Professor hailee = new Professor(
                    "012340", "Hailee Steinfeld", "01234568", "hailee@test.com", "006549823",
                    "Av. Daniel, 254", LocalDate.of(1998, 12, 24)
            );
            professorRepository.saveAll(List.of(brad, selena, hailee));
        };
    }
}
