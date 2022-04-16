package com.collegeapp.management.service;

import com.collegeapp.management.entity.Student;
import com.collegeapp.management.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
        if (notExistsStudentWithEmail(student.getEmail())) {
            try {
                Student savedStudent = studentRepository.save(student);
                return Optional.of(savedStudent);
            } catch (Exception psqlException) {
                System.out.println(Arrays.toString(psqlException.getStackTrace()));
            }
        }
        return Optional.empty();
    }

    public boolean notExistsStudentWithEmail(String email) {
        return studentRepository.findStudentByEmail(email).isEmpty();
    }

    public Optional<Student> getStudentById(String id) {
        return studentRepository.findById(id);
    }

    public boolean deleteStudentById(String id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }

        return false;
    }

    public Optional<Student> updateStudent(String id, Student student) {
        Optional<Student> existingStudent = getStudentById(id);

        if (existingStudent.isPresent()) {
            int updateResult = 0;

            if (existingStudent.get().getEmail().equals(student.getEmail())) {
                updateResult = studentRepository.updateStudentDetails(student.getFirstName(),
                        student.getLastName(),
                        student.getAddress(),
                        student.getDni(),
                        student.getDob(),
                        student.getTelephone(),
                        id);
            } else if (studentRepository.findStudentByEmail(student.getEmail()).isEmpty()){
                updateResult = studentRepository.updateStudent(student.getFirstName(),
                        student.getLastName(),
                        student.getAddress(),
                        student.getDni(),
                        student.getEmail(),
                        student.getDob(),
                        student.getTelephone(),
                        id);
            }

            if (updateResult > 0) {
                student.setId(id);
                return Optional.of(student);
            }
        }

        return Optional.empty();
    }
}
