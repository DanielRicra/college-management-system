package com.collegeapp.management.service;

import com.collegeapp.management.entity.Classroom;
import com.collegeapp.management.entity.projections.ClassroomSummary;
import com.collegeapp.management.repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<ClassroomSummary> getClassrooms() {
        return classroomRepository.findAllClassrooms();
    }

    public Optional<Classroom> getClassroomById(Integer classroomId) {
        return classroomRepository.findById(classroomId);
    }

    public boolean deleteClassroomById(Integer classroomId) {
        boolean existingClassroom = classroomRepository.findById(classroomId).isPresent();
        if (existingClassroom){
            classroomRepository.deleteById(classroomId);
            return true;
        }
        return false;
    }
}
