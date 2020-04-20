package br.com.ibm.marvel.filme.repository;

import br.com.ibm.marvel.filme.model.FilmeEntity;
import br.com.ibm.marvel.poder.model.PoderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmeRepository extends JpaRepository<FilmeEntity, Long> {
}
