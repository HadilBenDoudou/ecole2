package com.ecole.ecole.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    // Récupérer toutes les matières
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    // Ajouter une matière
    public Subject addSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    // Modifier une matière
    public Subject updateSubject(Long id, Subject subjectDetails) {
        Optional<Subject> subject = subjectRepository.findById(id);
        if (subject.isPresent()) {
            Subject existingSubject = subject.get();
            existingSubject.setName(subjectDetails.getName());
            existingSubject.setLevel(subjectDetails.getLevel());
            return subjectRepository.save(existingSubject);
        }
        return null; // Ou lancer une exception si nécessaire
    }

    // Supprimer une matière
    public void deleteSubject(Long id) {
        subjectRepository.deleteById(id);
    }

    // Trouver une matière par son ID
    public Optional<Subject> getSubjectById(Long id) {
        return subjectRepository.findById(id);
    }
}
