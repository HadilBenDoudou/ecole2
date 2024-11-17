package com.ecole.ecole.repositories;

import com.ecole.ecole.models.EleveEtude;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EleveEtudeRepository extends JpaRepository<EleveEtude, Long> {

}
