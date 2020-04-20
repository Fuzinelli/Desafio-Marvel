package br.com.ibm.marvel.heroi.model;

import br.com.ibm.marvel.poder.model.PoderSaida;
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
public class HeroiSaida {
    private Long id;
    private String nome;
    private List<PoderSaida> poderes;

}
