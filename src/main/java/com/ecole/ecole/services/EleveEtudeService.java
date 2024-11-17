package com.ecole.ecole.services;

import com.ecole.ecole.models.Eleve;
import com.ecole.ecole.models.EleveEtude;
import com.ecole.ecole.models.Etude;
import com.ecole.ecole.repositories.EleveEtudeRepository;
import com.ecole.ecole.repositories.EleveRepository;
import com.ecole.ecole.repositories.EtudeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EleveEtudeService {
    @Autowired
    private EleveEtudeRepository eleveEtudeRepository;

    public List<EleveEtude> getAllEleveEtudes() {
        return eleveEtudeRepository.findAll();
    }

    public Optional<EleveEtude> getEleveEtudeById(Long id) {
        return eleveEtudeRepository.findById(id);
    }


    @Autowired
    private EleveRepository eleveRepository;

    @Autowired
    private EtudeRepository etudeRepository;

    public EleveEtude saveEleveEtude(EleveEtude eleveEtude) {
        // Vérifiez si l'élève et l'étude existent
        Optional<Eleve> eleveOpt = eleveRepository.findById(eleveEtude.getEleve().getId_eleve());
        Optional<Etude> etudeOpt = etudeRepository.findById(eleveEtude.getEtude().getId_etude());

        if (eleveOpt.isPresent() && etudeOpt.isPresent()) {
            eleveEtude.setEleve(eleveOpt.get());
            eleveEtude.setEtude(etudeOpt.get());
            return eleveEtudeRepository.save(eleveEtude);
        } else {
            throw new RuntimeException("Élève ou Étude non trouvés");
        }
    }

    public void deleteEleveEtude(Long id) {
        eleveEtudeRepository.deleteById(id);
    }
}
