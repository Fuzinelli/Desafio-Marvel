package br.com.ibm.marvel.heroi.model;

import br.com.ibm.marvel.poder.model.PoderEntrada;
import br.com.ibm.marvel.revista.model.RevistaEntrada;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HeroiEntrada {
    private String nome;
    private List<Long> poderes;
}
