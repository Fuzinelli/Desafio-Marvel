package br.com.ibm.marvel.revista.model;

import br.com.ibm.marvel.heroi.model.HeroiEntrada;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RevistaEntrada {
    private String nome;
    private List<Long> herois;
}
