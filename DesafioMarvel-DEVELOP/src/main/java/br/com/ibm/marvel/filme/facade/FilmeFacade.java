package br.com.ibm.marvel.filme.facade;

import br.com.ibm.marvel.ator.model.AtorEntity;
import br.com.ibm.marvel.ator.repository.AtorRepository;
import br.com.ibm.marvel.filme.mapper.FilmeMapper;
import br.com.ibm.marvel.filme.model.FilmeEntity;
import br.com.ibm.marvel.filme.model.FilmeEntrada;
import br.com.ibm.marvel.filme.model.FilmeSaida;
import br.com.ibm.marvel.filme.repository.FilmeRepository;
import br.com.ibm.marvel.heroi.mapper.HeroiMapper;
import br.com.ibm.marvel.heroi.model.HeroiEntity;
import br.com.ibm.marvel.heroi.model.HeroiEntrada;
import br.com.ibm.marvel.heroi.model.HeroiSaida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class FilmeFacade {

    @Autowired
    FilmeRepository filmeRepository;

    @Autowired
    AtorRepository atorRepository;

    public FilmeSaida salvar(FilmeEntrada entrada) throws Exception {

        List<AtorEntity> atores = new ArrayList<>();
        if (entrada.getAtores().size() > 5 || entrada.getAtores().size() == 0) {
            throw new Exception("Quantidade de atores invalida");
        }
        for (int i = 0; i < entrada.getAtores().size(); i++) {
            Optional<AtorEntity> atoresNoBanco = atorRepository.findById(entrada.getAtores().get(i));
            if (atoresNoBanco.isPresent()) {
                atores.add(atoresNoBanco.get());
            }
        }

        FilmeEntity entidade = FilmeMapper.INSTANCE.converterEntradaParaEntity(entrada, atores);
        entidade = filmeRepository.save(entidade);
        FilmeSaida saida = FilmeMapper.INSTANCE.converterEntityParaSaida(entidade);
        return saida;
    }


    public List<FilmeSaida> listarFilmes() {

        List<FilmeEntity> lista = filmeRepository.findAll();

        List<FilmeSaida> listaSaida = new ArrayList<>();
        for (FilmeEntity saida : lista) {
            listaSaida.add(FilmeMapper.INSTANCE.converterEntityParaSaida(saida));
        }

        return listaSaida;
    }

    public FilmeSaida alterarNome(Long idFilme, FilmeEntrada entrada) throws Exception {

        FilmeEntity entidade;
        Optional<FilmeEntity> retornoBanco = filmeRepository.findById(idFilme);
        if (!retornoBanco.isPresent()) {
            throw new Exception("Nao encontrado");

        }
        entidade = retornoBanco.get();

        entidade.setNome(entrada.getNome());

        entidade = filmeRepository.save(entidade);

        return FilmeMapper.INSTANCE.converterEntityParaSaida(entidade);
    }
}
