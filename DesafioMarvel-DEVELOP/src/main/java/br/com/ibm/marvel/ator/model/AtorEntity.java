package br.com.ibm.marvel.ator.model;



import br.com.ibm.marvel.filme.model.FilmeEntity;
import br.com.ibm.marvel.heroi.model.HeroiEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Ator")
public class AtorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "nome")
    String nomeAtor;

    @OneToOne
    HeroiEntity heroi;


}
