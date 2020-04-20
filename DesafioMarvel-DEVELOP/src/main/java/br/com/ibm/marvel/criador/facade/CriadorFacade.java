package br.com.ibm.marvel.criador.facade;


import br.com.ibm.marvel.ator.mapper.AtorMapper;
import br.com.ibm.marvel.ator.model.AtorEntity;
import br.com.ibm.marvel.ator.model.AtorSaida;
import br.com.ibm.marvel.criador.mapper.CriadorMapper;
import br.com.ibm.marvel.criador.model.CriadorEntity;
import br.com.ibm.marvel.criador.model.CriadorEntrada;
import br.com.ibm.marvel.criador.model.CriadorSaida;
import br.com.ibm.marvel.criador.repository.CriadorRepository;
import br.com.ibm.marvel.filme.mapper.FilmeMapper;
import br.com.ibm.marvel.filme.model.FilmeEntity;
import br.com.ibm.marvel.filme.model.FilmeEntrada;
import br.com.ibm.marvel.filme.model.FilmeSaida;
import br.com.ibm.marvel.filme.repository.FilmeRepository;
import br.com.ibm.marvel.heroi.model.HeroiEntity;
import br.com.ibm.marvel.heroi.repository.HeroiRepository;
import br.com.ibm.marvel.poder.model.PoderEntity;
import br.com.ibm.marvel.revista.model.RevistaEntity;
import br.com.ibm.marvel.revista.repository.RevistaRepository;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CriadorFacade {

    @Autowired
    CriadorRepository criadorRepository;

    @Autowired
    FilmeRepository filmeRepository;

    @Autowired
    RevistaRepository revistaRepository;

    @Autowired
    HeroiRepository heroiRepository;

    public CriadorSaida salvar(CriadorEntrada entrada) throws Exception{

        if (quantidade(entrada) == false){
            throw new Exception("Quantia invalida");
        }
        List<HeroiEntity> herois = new ArrayList<>();
          for (int i = 0; i < entrada.getHerois().size(); i++) {
            Optional<HeroiEntity> heroisBanco = heroiRepository.findById(entrada.getHerois().get(i));
            if (heroisBanco.isPresent()) {
                herois.add(heroisBanco.get());
            }
        }
        List<RevistaEntity> revistas = new ArrayList<>();
        for (int i = 0; i < entrada.getRevistas().size(); i++) {
            Optional<RevistaEntity> revistasBanco = revistaRepository.findById(entrada.getRevistas().get(i));
            if (revistasBanco.isPresent()) {
                revistas.add(revistasBanco.get());
            }
        }
        List<FilmeEntity> filmes = new ArrayList<>();
        for (int i = 0; i < entrada.getFilmes().size(); i++) {
            Optional<FilmeEntity> filmesBanco = filmeRepository.findById(entrada.getFilmes().get(i));
            if (filmesBanco.isPresent()) {
                filmes.add(filmesBanco.get());
            }
        }
        CriadorEntity entidade = CriadorMapper.Instance.converterEntradaParaEntity(entrada, herois, filmes, revistas);
        entidade = criadorRepository.save(entidade);
        CriadorSaida saida = CriadorMapper.Instance.converterEntityParaSaida(entidade);
        return saida;
    }

    public List<CriadorSaida> listarCriadores() {

        List<CriadorEntity> lista = criadorRepository.findAll();

        List<CriadorSaida> listaSaida = new ArrayList<>();
        for (CriadorEntity saida : lista) {
            listaSaida.add(CriadorMapper.Instance.converterEntityParaSaida(saida));
        }

        return listaSaida;
    }

    public Boolean quantidade(CriadorEntrada criadorEntrada) {
        if (criadorEntrada.getFilmes().size() + criadorEntrada.getHerois().size() + criadorEntrada.getRevistas().size() > 5) {
            return false;
        }
        return true;
    }

    public CriadorSaida alterarNome(Long idCriador, CriadorEntrada entrada) throws Exception {

        CriadorEntity entidade;
        Optional<CriadorEntity> retornoBanco = criadorRepository.findById(idCriador);
        if (!retornoBanco.isPresent()) {
            throw new Exception("Nao encontrado");

        }
        entidade = retornoBanco.get();

        entidade.setNome(entrada.getNome());

        entidade = criadorRepository.save(entidade);

        return CriadorMapper.Instance.converterEntityParaSaida(entidade);
    }

}
