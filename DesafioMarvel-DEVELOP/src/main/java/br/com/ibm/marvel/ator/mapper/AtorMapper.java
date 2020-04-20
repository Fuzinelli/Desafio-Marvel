package br.com.ibm.marvel.ator.mapper;

import br.com.ibm.marvel.ator.model.AtorEntity;
import br.com.ibm.marvel.ator.model.AtorEntrada;
import br.com.ibm.marvel.ator.model.AtorSaida;
import br.com.ibm.marvel.heroi.model.HeroiEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;




@Mapper
public interface AtorMapper {

    AtorMapper INSTANCE = Mappers.getMapper(AtorMapper.class);


    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "heroi", target = "heroi")
    })

    AtorEntity converterEntradaParaEntity(AtorEntrada atorEntrada, HeroiEntity heroi);

    AtorSaida converterEntityParaSaida(AtorEntity atorEntity);

}
