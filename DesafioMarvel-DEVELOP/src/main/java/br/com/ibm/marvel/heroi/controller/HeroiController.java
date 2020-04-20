package br.com.ibm.marvel.heroi.controller;

import br.com.ibm.marvel.heroi.facade.HeroiFacade;
import br.com.ibm.marvel.heroi.model.HeroiEntrada;
import br.com.ibm.marvel.heroi.model.HeroiSaida;
import br.com.ibm.marvel.poder.model.PoderEntrada;
import br.com.ibm.marvel.poder.model.PoderSaida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "marvel/heroi", produces = "application/json")
@Configuration
@CrossOrigin
public class HeroiController {
    @Autowired
    HeroiFacade heroiFacade;

    @PostMapping
    public HeroiSaida salvar(@RequestBody HeroiEntrada heroiEntrada) throws Exception {
        HeroiSaida heroiSaida = heroiFacade.salvarHeroi(heroiEntrada);
        return heroiSaida;
    }

    @GetMapping
    public List<HeroiSaida> listarHerois() {
        List<HeroiSaida> saida = heroiFacade.listarHerois();

        return saida;
    }


    @PatchMapping("/{id}/alterarNome")
    public HeroiSaida alterarNome(@PathVariable Long id, @RequestBody HeroiEntrada heroiEntrada) throws Exception {

        HeroiSaida saida = heroiFacade.alterarNome(id, heroiEntrada);

        return saida;
    }
}
