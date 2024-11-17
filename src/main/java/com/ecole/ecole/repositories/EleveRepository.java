package com.ecole.ecole.repositories;

import com.ecole.ecole.models.Eleve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface EleveRepository extends JpaRepository<Eleve, Long> {
}

