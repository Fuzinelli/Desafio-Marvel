package br.com.ibm.marvel.ator.model;


import br.com.ibm.marvel.filme.model.FilmeSaida;
import br.com.ibm.marvel.heroi.model.HeroiSaida;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AtorSaida {

    private Long id;
    private String nomeAtor;
    private HeroiSaida heroi;


}
