package br.com.ibm.marvel.heroi.facade;

import br.com.ibm.marvel.heroi.mapper.HeroiMapper;
import br.com.ibm.marvel.heroi.model.HeroiEntity;
import br.com.ibm.marvel.heroi.model.HeroiEntrada;
import br.com.ibm.marvel.heroi.model.HeroiSaida;
import br.com.ibm.marvel.heroi.repository.HeroiRepository;
import br.com.ibm.marvel.poder.mapper.PoderMapper;
import br.com.ibm.marvel.poder.model.PoderEntity;
import br.com.ibm.marvel.poder.model.PoderEntrada;
import br.com.ibm.marvel.poder.model.PoderSaida;
import br.com.ibm.marvel.poder.repository.PoderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HeroiFacade {
    @Autowired
    HeroiRepository heroiRepository;
    @Autowired
    PoderRepository poderRepository;

    public HeroiSaida salvarHeroi(HeroiEntrada entrada) throws Exception {
        List<PoderEntity> poderes = new ArrayList<>();
        if (entrada.getPoderes().size() > 5 || entrada.getPoderes().size() == 0) {
            throw new Exception("Quantidade de poderes invalida");
        }

        for (int i = 0; i < entrada.getPoderes().size(); i++) {
            Optional<PoderEntity> poderesBanco = poderRepository.findById(entrada.getPoderes().get(i));
            if (poderesBanco.isPresent()) {
                poderes.add(poderesBanco.get());
            }
        }
        HeroiEntity entidade = HeroiMapper.Instance.converterEntradaParaEntity(entrada, poderes);
        entidade = heroiRepository.save(entidade);
        HeroiSaida saida = HeroiMapper.Instance.converterEntityParaSaida(entidade);
        return saida;
    }

    public List<HeroiSaida> listarHerois() {

        List<HeroiEntity> lista = heroiRepository.findAll();

        List<HeroiSaida> listaSaida = new ArrayList<>();
        for (HeroiEntity saida : lista) {
            listaSaida.add(HeroiMapper.Instance.converterEntityParaSaida(saida));
        }

        return listaSaida;
    }

    public HeroiSaida alterarNome(Long idHeroi, HeroiEntrada entrada) throws Exception {

        HeroiEntity entidade;
        Optional<HeroiEntity> retornoBanco = heroiRepository.findById(idHeroi);
        if (!retornoBanco.isPresent()) {
            throw new Exception("Nao encontrado");

        }
        entidade = retornoBanco.get();

        entidade.setNome(entrada.getNome());

        entidade = heroiRepository.save(entidade);

        return HeroiMapper.Instance.converterEntityParaSaida(entidade);
    }
}
