package br.com.ibm.marvel.revista.controller;

import br.com.ibm.marvel.filme.model.FilmeEntrada;
import br.com.ibm.marvel.filme.model.FilmeSaida;
import br.com.ibm.marvel.revista.facade.RevistaFacade;
import br.com.ibm.marvel.revista.model.RevistaEntrada;
import br.com.ibm.marvel.revista.model.RevistaSaida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "marvel/revista", produces = "application/json")
@Configuration
@CrossOrigin
public class RevistaController {
    @Autowired
    RevistaFacade revistaFacade;

    @PostMapping
    public RevistaSaida salvar(@RequestBody RevistaEntrada revistaEntrada) throws Exception{
        RevistaSaida revistaSaida = revistaFacade.salvarRevista(revistaEntrada);
        return revistaSaida;
    }

    @GetMapping
    public List<RevistaSaida> listarRevistas() {

        List<RevistaSaida> saida = revistaFacade.listarRevistas();

        return saida;
    }

    @PatchMapping("/{id}/alterarNome")
    public RevistaSaida alterarNome(@PathVariable Long id, @RequestBody RevistaEntrada revistaEntrada) throws Exception {

        RevistaSaida saida = revistaFacade.alterarNome(id, revistaEntrada);

        return saida;
    }
}
