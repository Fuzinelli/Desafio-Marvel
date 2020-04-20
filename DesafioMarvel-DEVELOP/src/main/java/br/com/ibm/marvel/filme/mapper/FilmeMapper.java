package br.com.ibm.marvel.filme.mapper;

import br.com.ibm.marvel.ator.model.AtorEntity;
import br.com.ibm.marvel.filme.model.FilmeEntity;
import br.com.ibm.marvel.filme.model.FilmeEntrada;
import br.com.ibm.marvel.filme.model.FilmeSaida;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface FilmeMapper {

    FilmeMapper INSTANCE = Mappers.getMapper(FilmeMapper.class);

    @Mappings({
        @Mapping(source = "atores", target = "atores"),
    })

    FilmeEntity converterEntradaParaEntity(FilmeEntrada filmeEntrada, List<AtorEntity> atores);

    FilmeSaida converterEntityParaSaida(FilmeEntity filmeEntity);

}
