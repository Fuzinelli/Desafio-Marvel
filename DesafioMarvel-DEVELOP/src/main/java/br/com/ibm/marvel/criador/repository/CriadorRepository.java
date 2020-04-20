package br.com.ibm.marvel.criador.repository;

import br.com.ibm.marvel.criador.model.CriadorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriadorRepository extends JpaRepository<CriadorEntity, Long> {
}
