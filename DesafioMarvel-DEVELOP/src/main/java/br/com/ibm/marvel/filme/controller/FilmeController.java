package br.com.ibm.marvel.filme.controller;

import br.com.ibm.marvel.criador.model.CriadorSaida;
import br.com.ibm.marvel.filme.facade.FilmeFacade;
import br.com.ibm.marvel.filme.mapper.FilmeMapper;
import br.com.ibm.marvel.filme.model.FilmeEntity;
import br.com.ibm.marvel.filme.model.FilmeEntrada;
import br.com.ibm.marvel.filme.model.FilmeSaida;
import br.com.ibm.marvel.filme.repository.FilmeRepository;
import br.com.ibm.marvel.heroi.model.HeroiEntrada;
import br.com.ibm.marvel.heroi.model.HeroiSaida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "marvel/filme", produces = "application/json")
@Configuration
@CrossOrigin
public class FilmeController {

    @Autowired
    FilmeFacade filmeFacade;

    @PostMapping
    public FilmeSaida salvar(@RequestBody FilmeEntrada entrada) throws Exception {
        FilmeSaida saida = filmeFacade.salvar(entrada);
        return saida;
    }

    @GetMapping
    public List<FilmeSaida> listarFilmes() {
        List<FilmeSaida> saida = filmeFacade.listarFilmes();

        return saida;
    }

    @PatchMapping("/{id}/alterarNome")
    public FilmeSaida alterarNome(@PathVariable Long id, @RequestBody FilmeEntrada filmeEntrada) throws Exception {

        FilmeSaida saida = filmeFacade.alterarNome(id, filmeEntrada);

        return saida;
    }
}
