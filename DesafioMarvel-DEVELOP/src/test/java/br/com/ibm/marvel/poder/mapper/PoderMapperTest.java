package br.com.ibm.marvel.poder.mapper;

import br.com.ibm.marvel.poder.model.PoderEntity;
import br.com.ibm.marvel.poder.model.PoderEntrada;
import br.com.ibm.marvel.poder.model.PoderSaida;
import br.com.ibm.marvel.templates.PoderEntityTemplate;
import br.com.ibm.marvel.templates.PoderEntradaTemplate;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class PoderMapperTest {

    @Before
    public void setup(){
        FixtureFactoryLoader.loadTemplates("br.com.ibm.marvel.templates");
    }

    @Test
    public void deveConverterEntradaParaEntity(){
        //given
        PoderEntrada poderEntrada = Fixture.from(PoderEntrada.class).gimme(PoderEntradaTemplate.PODER_ENTRADA_VALIDO);

        //when
        PoderEntity poderEntity = PoderMapper.INSTANCE.converterEntradaParaEntity(poderEntrada);

        //then
        Assert.assertNotNull(poderEntity);
        Assert.assertEquals(poderEntrada.getDescricao(), poderEntity.getDescricao());


    }

    @Test
    public void deveConverterEntityParaSaida(){
        PoderEntity poderEntity = Fixture.from(PoderEntity.class).gimme(PoderEntityTemplate.PODER_TEMPLATE_VALIDO);

        PoderSaida poderSaida = PoderMapper.INSTANCE.converterEntityParaSaida(poderEntity);

        Assert.assertNotNull(poderEntity);
        Assert.assertEquals(poderEntity.getDescricao(), poderSaida.getDescricao());
        Assert.assertEquals(poderEntity.getId(), poderSaida.getId());
    }
}
