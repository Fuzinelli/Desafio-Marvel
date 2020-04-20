package br.com.ibm.marvel.heroi.repository;

import br.com.ibm.marvel.heroi.model.HeroiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroiRepository extends JpaRepository<HeroiEntity, Long> {
}
