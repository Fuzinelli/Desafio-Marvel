package br.com.ibm.marvel.criador.model;


import br.com.ibm.marvel.filme.model.FilmeSaida;
import br.com.ibm.marvel.heroi.model.HeroiSaida;
import br.com.ibm.marvel.revista.model.RevistaSaida;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CriadorSaida {

    private Long id;
    private String nome;
    private List<FilmeSaida> filmes;
    private List<HeroiSaida> herois;
    private List<RevistaSaida> revistas;

}
