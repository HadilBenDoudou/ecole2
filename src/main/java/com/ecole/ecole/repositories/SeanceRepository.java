package com.ecole.ecole.repositories;

import com.ecole.ecole.models.Seance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeanceRepository extends JpaRepository<Seance, Long> {
    // You can define custom query methods here if needed
}
