package br.com.ibm.marvel.ator.repository;

import br.com.ibm.marvel.ator.model.AtorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtorRepository extends JpaRepository<AtorEntity, Long> {
}
