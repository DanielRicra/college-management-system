package com.collegeapp.management.service;

import com.collegeapp.management.entity.Classroom;
import com.collegeapp.management.repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClassroomService {

    private final ClassroomRepository classroomRepository;

    @Autowired
    public ClassroomService(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    public Optional<Classroom> saveClassroom(Classroom classroom) {
        return Optional.of(classroomRepository.save(classroom));
    }
}
