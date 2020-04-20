package br.com.ibm.marvel.poder.mapper;

import br.com.ibm.marvel.poder.model.PoderEntity;
import br.com.ibm.marvel.poder.model.PoderEntrada;
import br.com.ibm.marvel.poder.model.PoderSaida;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PoderMapper {

    PoderMapper INSTANCE = Mappers.getMapper(PoderMapper.class);

    PoderEntity converterEntradaParaEntity(PoderEntrada poderEntrada);

    PoderSaida converterEntityParaSaida(PoderEntity poderEntity);



}
