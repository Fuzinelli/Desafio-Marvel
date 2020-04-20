package br.com.ibm.marvel.filme.model;

import br.com.ibm.marvel.ator.model.AtorSaida;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilmeSaida {
    private Long id;
    private String nome;
    private List<AtorSaida> atores;
}
