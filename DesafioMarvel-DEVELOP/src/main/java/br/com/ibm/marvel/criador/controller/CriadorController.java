package br.com.ibm.marvel.criador.controller;


import br.com.ibm.marvel.ator.model.AtorSaida;
import br.com.ibm.marvel.criador.facade.CriadorFacade;
import br.com.ibm.marvel.criador.model.CriadorEntrada;
import br.com.ibm.marvel.criador.model.CriadorSaida;
import br.com.ibm.marvel.filme.model.FilmeEntrada;
import br.com.ibm.marvel.filme.model.FilmeSaida;
import br.com.ibm.marvel.heroi.model.HeroiSaida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "marvel/criador", produces = "application/json")
@Configuration
@CrossOrigin
public class CriadorController {

    @Autowired
    CriadorFacade criadorFacade;

    @PostMapping
    CriadorSaida salvar (@RequestBody CriadorEntrada criadorEntrada) throws Exception{
        CriadorSaida criadorSaida = criadorFacade.salvar(criadorEntrada);
        return criadorSaida;
    }

    @GetMapping
    public List<CriadorSaida> listarAtores() {
        List<CriadorSaida> saida = criadorFacade.listarCriadores();

        return saida;
    }

    @PatchMapping("/{id}/alterarNome")
    public CriadorSaida alterarNome(@PathVariable Long id, @RequestBody CriadorEntrada criadorEntrada) throws Exception {

        CriadorSaida saida = criadorFacade.alterarNome(id, criadorEntrada);

        return saida;
    }
}
