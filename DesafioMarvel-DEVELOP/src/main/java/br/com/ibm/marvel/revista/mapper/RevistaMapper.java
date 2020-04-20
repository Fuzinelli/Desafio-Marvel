package br.com.ibm.marvel.revista.mapper;

import br.com.ibm.marvel.heroi.model.HeroiEntity;
import br.com.ibm.marvel.revista.model.RevistaEntity;
import br.com.ibm.marvel.revista.model.RevistaEntrada;
import br.com.ibm.marvel.revista.model.RevistaSaida;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RevistaMapper {
    RevistaMapper Instance = Mappers.getMapper(RevistaMapper.class);

    @Mappings({
            @Mapping(source = "herois", target = "herois")
    })

    RevistaEntity converterEntradaParaEntity(RevistaEntrada entrada, List<HeroiEntity> herois);
    RevistaSaida converterEntityParaSaida(RevistaEntity entidade);

}
