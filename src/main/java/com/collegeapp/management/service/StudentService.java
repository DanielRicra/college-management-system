package com.collegeapp.management.service;

import com.collegeapp.management.entity.Student;
import com.collegeapp.management.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> addStudent(Student student) {
        if (existsStudentWithEmail(student.getEmail())) {
            return Optional.empty();
        }
        try {
            Student savedStudent = studentRepository.save(student);
            return Optional.of(savedStudent);
        } catch (Exception psqlException) {
            psqlException.getStackTrace();
            return Optional.empty();
        }
    }

    public boolean existsStudentWithEmail(String email) {
        return studentRepository.findStudentByEmail(email).isPresent();
    }

    public Optional<Student> getStudentById(String id) {
        return studentRepository.findById(id);
    }

    public boolean deleteStudentById(String id) {
        boolean existingStudent = studentRepository.findById(id).isPresent();
        if (existingStudent) {
            studentRepository.deleteById(id);
            return true;
        }

        return false;
    }

    public Optional<Student> updateStudent(String id, Student student) {
        boolean existingStudent = getStudentById(id).isPresent();

        if (existingStudent && !existsStudentWithEmail(student.getEmail())) {

            int isUpdated = studentRepository.updateStudent(student.getFirstName(),
                                                                student.getLastName(),
                                                                student.getAddress(),
                                                                student.getDni(),
                                                                student.getEmail(),
                                                                student.getDob(),
                                                                student.getTelephone(),
                                                                id);
            if (isUpdated > 0) {
                student.setId(id);
                return Optional.of(student);
            }
        }

        return Optional.empty();
    }
}
