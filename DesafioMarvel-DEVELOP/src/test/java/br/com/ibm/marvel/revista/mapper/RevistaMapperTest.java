package br.com.ibm.marvel.revista.mapper;

import br.com.ibm.marvel.heroi.model.HeroiEntity;
import br.com.ibm.marvel.revista.model.RevistaEntity;
import br.com.ibm.marvel.revista.model.RevistaEntrada;
import br.com.ibm.marvel.revista.model.RevistaSaida;
import br.com.ibm.marvel.templates.HeroiEntityTemplate;
import br.com.ibm.marvel.templates.RevistaEntityTemplate;
import br.com.ibm.marvel.templates.RevistaEntradaTemplate;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class RevistaMapperTest {
    private RevistaMapper revistaMapper = Mappers.getMapper(RevistaMapper.class);

    @Before
    public void setup(){
        FixtureFactoryLoader.loadTemplates("br.com.ibm.marvel.templates");
    }

    @Test
    public void deveConverterEntradaParaEntity(){
        //given
        RevistaEntrada revistaEntrada = Fixture.from(RevistaEntrada.class).gimme(RevistaEntradaTemplate.REVISTA_ENTRADA_VALIDO);

        List<HeroiEntity> herois = Fixture.from(HeroiEntity.class).gimme(1, HeroiEntityTemplate.HEROI_TEMPLATE_VALIDO);

        //when
        RevistaEntity revistaEntity = revistaMapper.converterEntradaParaEntity(revistaEntrada, herois);

        //then
        Assert.assertNotNull(revistaEntity);
        Assert.assertEquals(revistaEntrada.getNome(), revistaEntity.getNome());
        Assert.assertEquals(revistaEntity.getHerois().get(0).getNome(), herois.get(0).getNome());
        Assert.assertEquals(revistaEntity.getHerois().get(0).getId(), herois.get(0).getId());
        Assert.assertEquals(revistaEntity.getHerois().get(0).getPoderes().get(0).getDescricao(), herois.get(0).getPoderes().get(0).getDescricao());
        Assert.assertEquals(revistaEntity.getHerois().get(0).getPoderes().get(0).getId(), herois.get(0).getPoderes().get(0).getId());
    }

    @Test
    public void deveConverterEntityParaSaida(){
        //given
        RevistaEntity revistaEntity = Fixture.from(RevistaEntity.class).gimme(RevistaEntityTemplate.REVISTA_TEMPLATE_VALIDO);

        //when
        RevistaSaida revistaSaida = revistaMapper.converterEntityParaSaida(revistaEntity);

        //then
        Assert.assertNotNull(revistaSaida);
        Assert.assertEquals(revistaEntity.getId(), revistaSaida.getId());
        Assert.assertEquals(revistaEntity.getNome(), revistaSaida.getNome());
        Assert.assertEquals(revistaEntity.getHerois().get(0).getNome(), revistaSaida.getHerois().get(0).getNome());
        Assert.assertEquals(revistaEntity.getHerois().get(0).getId(), revistaSaida.getHerois().get(0).getId());
        Assert.assertEquals(revistaEntity.getHerois().get(0).getPoderes().get(0).getDescricao(), revistaSaida.getHerois().get(0).getPoderes().get(0).getDescricao());
        Assert.assertEquals(revistaEntity.getHerois().get(0).getPoderes().get(0).getId(), revistaSaida.getHerois().get(0).getPoderes().get(0).getId());
    }
}
