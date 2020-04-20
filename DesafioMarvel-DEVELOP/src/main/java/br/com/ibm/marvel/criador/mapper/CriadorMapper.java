package br.com.ibm.marvel.criador.mapper;

import br.com.ibm.marvel.criador.model.CriadorEntity;
import br.com.ibm.marvel.criador.model.CriadorEntrada;
import br.com.ibm.marvel.criador.model.CriadorSaida;
import br.com.ibm.marvel.filme.model.FilmeEntity;
import br.com.ibm.marvel.heroi.mapper.HeroiMapper;
import br.com.ibm.marvel.heroi.model.HeroiEntity;
import br.com.ibm.marvel.revista.model.RevistaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CriadorMapper {

    CriadorMapper Instance = Mappers.getMapper(CriadorMapper.class);

    @Mappings({
            @Mapping(source = "herois", target = "herois"),
            @Mapping(source = "filmes", target = "filmes"),
            @Mapping(source = "revistas", target = "revistas")
    })


    CriadorEntity converterEntradaParaEntity(CriadorEntrada entrada, List<HeroiEntity> herois, List<FilmeEntity> filmes, List<RevistaEntity> revistas );
    CriadorSaida converterEntityParaSaida(CriadorEntity entidade);

}
