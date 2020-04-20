package br.com.ibm.marvel.revista.model;

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
public class RevistaSaida {
    private Long id;
    private String nome;
    private List<HeroiSaida> herois;
}
