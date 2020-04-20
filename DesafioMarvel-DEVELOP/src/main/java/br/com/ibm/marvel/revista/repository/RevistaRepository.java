package br.com.ibm.marvel.revista.repository;

import br.com.ibm.marvel.revista.model.RevistaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RevistaRepository extends JpaRepository<RevistaEntity, Long> {
}
