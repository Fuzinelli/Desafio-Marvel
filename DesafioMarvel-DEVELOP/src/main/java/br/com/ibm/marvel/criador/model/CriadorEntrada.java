package br.com.ibm.marvel.criador.model;


import br.com.ibm.marvel.filme.model.FilmeEntity;
import br.com.ibm.marvel.heroi.model.HeroiEntity;
import br.com.ibm.marvel.revista.model.RevistaEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CriadorEntrada {

    private String nome;
    private List<Long> filmes;
    private List<Long> revistas;
    private List<Long> herois;

}
