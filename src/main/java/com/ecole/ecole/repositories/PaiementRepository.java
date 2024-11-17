package com.ecole.ecole.repositories;

import com.ecole.ecole.models.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PaiementRepository extends JpaRepository<Paiement, Long> {
}


