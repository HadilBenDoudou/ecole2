package com.ecole.ecole.repositories;

import com.ecole.ecole.models.Etude;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface EtudeRepository extends JpaRepository<Etude, Long> {
}

