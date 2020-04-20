package br.com.ibm.marvel.heroi.model;

import br.com.ibm.marvel.poder.model.PoderEntity;
import br.com.ibm.marvel.revista.model.RevistaEntity;
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
@Entity(name = "heroi")
public class HeroiEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @ManyToMany
    @NotNull
    private List<PoderEntity> poderes;

}
