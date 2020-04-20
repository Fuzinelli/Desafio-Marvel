package br.com.ibm.marvel.poder.repository;

import br.com.ibm.marvel.poder.model.PoderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoderRepository extends JpaRepository<PoderEntity, Long> {
}
