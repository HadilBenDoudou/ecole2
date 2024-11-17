package com.ecole.ecole.services;

import com.ecole.ecole.models.Enseignant;
import com.ecole.ecole.repositories.EnseignantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnseignantService {

    @Autowired
    private EnseignantRepository enseignantRepository;

    // Récupérer tous les enseignants
    public List<Enseignant> getAllEnseignants() {
        return enseignantRepository.findAll();
    }

    // Récupérer un enseignant par son ID
    public Enseignant getEnseignantById(Long id) {
        Optional<Enseignant> enseignant = enseignantRepository.findById(id);
        return enseignant.orElse(null);
    }

    // Enregistrer ou mettre à jour un enseignant
    public Enseignant saveEnseignant(Enseignant enseignant) {
        return enseignantRepository.save(enseignant);
    }

    // Supprimer un enseignant par son ID
    public void deleteEnseignant(Long id) {
        enseignantRepository.deleteById(id);
    }
}
