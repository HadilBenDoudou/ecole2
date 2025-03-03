package com.ecole.ecole.services;

import com.ecole.ecole.models.Etude;
import com.ecole.ecole.models.Paiement;
import com.ecole.ecole.repositories.EtudeRepository;
import com.ecole.ecole.repositories.PaiementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaiementService {
    @Autowired
    private PaiementRepository paiementRepository;

    public List<Paiement> getAllPaiements() {
        return paiementRepository.findAll();
    }

    public Paiement getPaiementById(Long id) {
        return paiementRepository.findById(id).orElse(null);
    }

    @Autowired
    private EtudeRepository etudeRepository;

    public Paiement savePaiement(Paiement paiement) {
        // Vérifiez si l'étude existe et liez-la au paiement
        Optional<Etude> etude = etudeRepository.findById(paiement.getEtude().getId_etude());
        if (etude.isPresent()) {
            paiement.setEtude(etude.get());  // Associez l'étude complète
        } else {
            throw new RuntimeException("Etude non trouvée pour l'ID : " + paiement.getEtude().getId_etude());
        }

        return paiementRepository.save(paiement);
    }


    public void deletePaiement(Long id) {
        paiementRepository.deleteById(id);
    }
}
