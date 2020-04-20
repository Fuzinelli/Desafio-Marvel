package br.com.ibm.marvel.heroi.mapper;

import br.com.ibm.marvel.heroi.model.HeroiEntity;
import br.com.ibm.marvel.heroi.model.HeroiEntrada;
import br.com.ibm.marvel.heroi.model.HeroiSaida;
import br.com.ibm.marvel.poder.model.PoderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface HeroiMapper {
    HeroiMapper Instance = Mappers.getMapper(HeroiMapper.class);

    @Mappings({
            @Mapping(source = "poderes", target = "poderes"),
    })


    HeroiEntity converterEntradaParaEntity(HeroiEntrada entrada, List<PoderEntity> poderes);
    HeroiSaida converterEntityParaSaida(HeroiEntity entidade);
}
