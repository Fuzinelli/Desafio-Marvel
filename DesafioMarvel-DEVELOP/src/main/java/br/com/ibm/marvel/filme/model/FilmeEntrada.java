package br.com.ibm.marvel.filme.model;

import br.com.ibm.marvel.ator.model.AtorEntrada;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilmeEntrada {
    private String nome;
    private List<Long> atores;
}
