package br.com.ibm.marvel.criador.model;


import br.com.ibm.marvel.filme.model.FilmeEntity;
import br.com.ibm.marvel.heroi.model.HeroiEntity;
import br.com.ibm.marvel.revista.model.RevistaEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Criador")
public class CriadorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "nome")
    String nome;

    @OneToMany(cascade = CascadeType.ALL)
    List<FilmeEntity> filmes = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    List<RevistaEntity> revistas = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    List<HeroiEntity> herois = new ArrayList<>();

}
