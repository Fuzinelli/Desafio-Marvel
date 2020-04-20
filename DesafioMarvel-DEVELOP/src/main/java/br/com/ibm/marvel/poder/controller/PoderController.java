package br.com.ibm.marvel.poder.controller;


import br.com.ibm.marvel.poder.facade.PoderFacade;
import br.com.ibm.marvel.poder.model.PoderEntrada;
import br.com.ibm.marvel.poder.model.PoderSaida;
import br.com.ibm.marvel.revista.model.RevistaSaida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "marvel/poder", produces = "application/json")
@Configuration
@CrossOrigin
public class PoderController {

    @Autowired
    public PoderFacade poderFacade;


    @PostMapping
    public PoderSaida salvar(@RequestBody PoderEntrada poderEntrada) {
        PoderSaida saida = poderFacade.salvar(poderEntrada);
        return saida;
    }

    @GetMapping
    public List<PoderSaida> listarPoderes() {

        List<PoderSaida> saida = poderFacade.listarPoderes();

        return saida;
    }

    @PatchMapping("/{id}/alterarDescricao")
    public PoderSaida alterarIdade(@PathVariable Long id, @RequestBody PoderEntrada poderEntrada) throws Exception {

        PoderSaida saida = poderFacade.alterarDescricao(id, poderEntrada);

        return saida;
    }

}
