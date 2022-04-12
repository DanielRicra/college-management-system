package com.collegeapp.management.service;

import com.collegeapp.management.entity.Student;
import com.collegeapp.management.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
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

    public Optional<Student> addNewStudent(Student student) {
        Optional<Student> existingStudent = studentRepository
                .findStudentByEmail(student.getEmail());
        if (existingStudent.isPresent()) {
            return Optional.empty();
        }
        Student save = studentRepository.save(student);
        return Optional.of(save);
    }

    public Optional<Student> getStudentById(String id) {
        Optional<Student> studentById = studentRepository.findStudentById(id);
        System.out.println("Service" + studentById);
        return studentById;
    }

}
