package br.com.ibm.marvel.poder.facade;

import br.com.ibm.marvel.poder.mapper.PoderMapper;
import br.com.ibm.marvel.poder.model.PoderEntity;
import br.com.ibm.marvel.poder.model.PoderEntrada;
import br.com.ibm.marvel.poder.model.PoderSaida;
import br.com.ibm.marvel.poder.repository.PoderRepository;
import br.com.ibm.marvel.revista.mapper.RevistaMapper;
import br.com.ibm.marvel.revista.model.RevistaEntity;
import br.com.ibm.marvel.revista.model.RevistaSaida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PoderFacade {

    @Autowired
    PoderRepository poderRepository;

    public PoderSaida salvar(PoderEntrada poderEntrada) {

        // ENTRADA PARA ENTIDADE =>
        PoderEntity entidade = PoderMapper.INSTANCE.converterEntradaParaEntity(poderEntrada);

        // ENTIDADE PARA BANCO =>
        entidade = poderRepository.save(entidade);

        // ENTIDADE PARA SAIDA
        PoderSaida saida = PoderMapper.INSTANCE.converterEntityParaSaida(entidade);

        return saida;
    }

    public List<PoderSaida> listarPoderes() {

        List<PoderEntity> lista = poderRepository.findAll();

        List<PoderSaida> listaSaida = new ArrayList<>();
        for (PoderEntity saida : lista) {
            listaSaida.add(PoderMapper.INSTANCE.converterEntityParaSaida(saida));
        }

        return listaSaida;
    }

    public PoderSaida alterarDescricao(Long idPoder, PoderEntrada entrada) throws Exception {

        PoderEntity entidade;
        Optional<PoderEntity> retornoBanco = poderRepository.findById(idPoder);
        if (!retornoBanco.isPresent()) {
            throw new Exception("Nao encontrado");

        }
        entidade = retornoBanco.get();

        entidade.setDescricao(entrada.getDescricao());

        entidade = poderRepository.save(entidade);

        return PoderMapper.INSTANCE.converterEntityParaSaida(entidade);
    }

    public List<PoderEntity> buscarListaPoderesEntityPorId(List<Long> listaIds) {
        return poderRepository.findAllById(listaIds);
    }
}
