package br.com.ibm.marvel.revista.facade;

import br.com.ibm.marvel.ator.model.AtorEntity;
import br.com.ibm.marvel.filme.mapper.FilmeMapper;
import br.com.ibm.marvel.filme.model.FilmeEntity;
import br.com.ibm.marvel.filme.model.FilmeEntrada;
import br.com.ibm.marvel.filme.model.FilmeSaida;
import br.com.ibm.marvel.heroi.model.HeroiEntity;
import br.com.ibm.marvel.heroi.repository.HeroiRepository;
import br.com.ibm.marvel.revista.mapper.RevistaMapper;
import br.com.ibm.marvel.revista.model.RevistaEntity;
import br.com.ibm.marvel.revista.model.RevistaEntrada;
import br.com.ibm.marvel.revista.model.RevistaSaida;
import br.com.ibm.marvel.revista.repository.RevistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RevistaFacade {
    @Autowired
    RevistaRepository revistaRepository;

    @Autowired
    HeroiRepository heroiRepository;

    public RevistaSaida salvarRevista(RevistaEntrada entrada) throws Exception {

        List<HeroiEntity> herois = new ArrayList<>();
        if (entrada.getHerois().size() > 5 || entrada.getHerois().size() == 0) {
            throw new Exception("Quantidade de herois invalida");
        }
        for (int i = 0; i < entrada.getHerois().size(); i++) {
            Optional<HeroiEntity> heroisNoBanco = heroiRepository.findById(entrada.getHerois().get(i));
            if (heroisNoBanco.isPresent()) {
                herois.add(heroisNoBanco.get());
            }
        }
        RevistaEntity entidade = RevistaMapper.Instance.converterEntradaParaEntity(entrada, herois);
        entidade = revistaRepository.save(entidade);
        RevistaSaida saida = RevistaMapper.Instance.converterEntityParaSaida(entidade);
        return saida;
    }

    public List<RevistaSaida> listarRevistas() {

        List<RevistaEntity> lista = revistaRepository.findAll();

        List<RevistaSaida> listaSaida = new ArrayList<>();
        for (RevistaEntity saida : lista) {
            listaSaida.add(RevistaMapper.Instance.converterEntityParaSaida(saida));
        }

        return listaSaida;
    }

    public RevistaSaida alterarNome(Long idRevista, RevistaEntrada entrada) throws Exception {

        RevistaEntity entidade;
        Optional<RevistaEntity> retornoBanco = revistaRepository.findById(idRevista);
        if (!retornoBanco.isPresent()) {
            throw new Exception("Nao encontrado");

        }
        entidade = retornoBanco.get();

        entidade.setNome(entrada.getNome());

        entidade = revistaRepository.save(entidade);

        return RevistaMapper.Instance.converterEntityParaSaida(entidade);
    }
}
