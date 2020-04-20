package br.com.ibm.marvel.ator.facade;

import br.com.ibm.marvel.ator.mapper.AtorMapper;
import br.com.ibm.marvel.ator.model.AtorEntity;
import br.com.ibm.marvel.ator.model.AtorEntrada;
import br.com.ibm.marvel.ator.model.AtorSaida;
import br.com.ibm.marvel.ator.repository.AtorRepository;
import br.com.ibm.marvel.filme.mapper.FilmeMapper;
import br.com.ibm.marvel.filme.model.FilmeEntity;
import br.com.ibm.marvel.filme.model.FilmeEntrada;
import br.com.ibm.marvel.filme.model.FilmeSaida;
import br.com.ibm.marvel.filme.repository.FilmeRepository;
import br.com.ibm.marvel.heroi.model.HeroiEntity;
import br.com.ibm.marvel.heroi.repository.HeroiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class AtorFacade {

    @Autowired
    AtorRepository atorRepository;

    @Autowired
    HeroiRepository heroiRepository;


    public AtorSaida salvar(AtorEntrada atorEntrada) {

        // ENTRADA PARA ENTIDADE =>
        HeroiEntity heroi = null;
        Optional<HeroiEntity> optionalHeroi = heroiRepository.findById(atorEntrada.getHeroi());
        if (optionalHeroi.isPresent()) {
            heroi = optionalHeroi.get();

        }
        AtorEntity entidade = AtorMapper.INSTANCE.converterEntradaParaEntity(atorEntrada, heroi);

        // ENTIDADE PARA BANCO =>
        entidade = atorRepository.save(entidade);

        // ENTIDADE PARA SAIDA
        AtorSaida saida = AtorMapper.INSTANCE.converterEntityParaSaida(entidade);

        return saida;
    }

    public List<AtorSaida> listarAtores() {

        List<AtorEntity> lista = atorRepository.findAll();

        List<AtorSaida> listaSaida = new ArrayList<>();
        for (AtorEntity saida : lista) {
            listaSaida.add(AtorMapper.INSTANCE.converterEntityParaSaida(saida));
        }

        return listaSaida;
    }

    public AtorSaida alterarNome(Long idAtor, AtorEntrada entrada) throws Exception {

        AtorEntity entidade;
        Optional<AtorEntity> retornoBanco = atorRepository.findById(idAtor);
        if (!retornoBanco.isPresent()) {
            throw new Exception("Nao encontrado");

        }
        entidade = retornoBanco.get();

        entidade.setNomeAtor(entrada.getNomeAtor());

        entidade = atorRepository.save(entidade);

        return AtorMapper.INSTANCE.converterEntityParaSaida(entidade);
    }

}
