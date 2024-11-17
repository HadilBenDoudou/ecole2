package com.ecole.ecole.repositories;

import com.ecole.ecole.models.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface EnseignantRepository extends JpaRepository<Enseignant, Long> {
}

