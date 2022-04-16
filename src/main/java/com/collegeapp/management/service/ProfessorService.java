package com.collegeapp.management.service;

import com.collegeapp.management.entity.Professor;
import com.collegeapp.management.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    @Autowired
    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }


    public List<Professor> getProfessors() {
        return professorRepository.findAll();
    }

    public Optional<Professor> saveProfessor(Professor professor) {

        if (!existsProfessorWithEmail(professor.getEmail())) {
            try {
                return Optional.of(professorRepository.save(professor));
            } catch (Exception psqlException) {
                psqlException.getStackTrace();
            }
        }
        return Optional.empty();
    }

    public boolean existsProfessorWithEmail(String email) {
        return professorRepository.findProfessorByEmail(email).isPresent();
    }

    public Optional<Professor> getProfessorById(String professorId) {
        return professorRepository.findById(professorId);
    }

    public Optional<Professor> updateProfessor(Professor professor, String professorId) {
        Optional<Professor> optionalProfessor = professorRepository.findById(professorId);
        if (optionalProfessor.isPresent()) {
            int result = 0;
            if (optionalProfessor.get().getEmail().equals(professor.getEmail())) {
                result = professorRepository.updateProfessorDetails(professor.getFullName(),
                        professor.getDni(),
                        professor.getAddress(),
                        professor.getTelephone(),
                        professor.getDob(),
                        professorId);
            } else if(professorRepository.findProfessorByEmail(professor.getEmail()).isEmpty()) {
                result = professorRepository.updateProfessor(professor.getFullName(),
                        professor.getDni(),
                        professor.getAddress(),
                        professor.getEmail(),
                        professor.getTelephone(),
                        professor.getDob(),
                        professorId);
            }
            if (result > 0) {
                professor.setId(professorId);
                return Optional.of(professor);
            }
        }
        return Optional.empty();
    }

    public boolean deleteProfessorById(String professorId) {
        if (professorRepository.existsById(professorId)) {
            professorRepository.deleteById(professorId);
            return true;
        }
        return false;
    }
}
