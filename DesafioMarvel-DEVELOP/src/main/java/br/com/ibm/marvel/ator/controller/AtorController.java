package br.com.ibm.marvel.ator.controller;

import br.com.ibm.marvel.ator.facade.AtorFacade;
import br.com.ibm.marvel.ator.model.AtorEntrada;
import br.com.ibm.marvel.ator.model.AtorSaida;
import br.com.ibm.marvel.filme.model.FilmeEntrada;
import br.com.ibm.marvel.filme.model.FilmeSaida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "marvel/ator", produces = "application/json")
@Configuration
@CrossOrigin
public class AtorController {

    @Autowired
    AtorFacade atorFacade;

    @PostMapping
    public AtorSaida salvar(@RequestBody AtorEntrada entrada) throws Exception {
        AtorSaida saida = atorFacade.salvar(entrada);
        return saida;
    }

    @GetMapping
    public List<AtorSaida> listarAtores() {
        List<AtorSaida> saida = atorFacade.listarAtores();

        return saida;
    }

    @PatchMapping("/{id}/alterarNome")
    public AtorSaida alterarNome(@PathVariable Long id, @RequestBody AtorEntrada atorEntrada) throws Exception {

        AtorSaida saida = atorFacade.alterarNome(id, atorEntrada);

        return saida;
    }

}
