package br.com.ibm.marvel.heroi.mapper;

import br.com.ibm.marvel.heroi.model.HeroiEntity;
import br.com.ibm.marvel.heroi.model.HeroiEntrada;
import br.com.ibm.marvel.heroi.model.HeroiSaida;
import br.com.ibm.marvel.poder.model.PoderEntity;
import br.com.ibm.marvel.templates.HeroiEntityTemplate;
import br.com.ibm.marvel.templates.HeroiEntradaTemplate;
import br.com.ibm.marvel.templates.PoderEntityTemplate;
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
public class HeroiMapperTest {
    private HeroiMapper heroiMapper = Mappers.getMapper(HeroiMapper.class);
    @Before
    public void setup() {
        FixtureFactoryLoader.loadTemplates("br.com.ibm.marvel.templates");
    }

    @Test
    public void deveConverterEntradaParaEntity(){
        //given

        HeroiEntrada heroiEntrada = Fixture.from(HeroiEntrada.class).gimme(HeroiEntradaTemplate.HEROI_ENTRADA_VALIDO);
        List<PoderEntity> poderes = Fixture.from(PoderEntity.class).gimme(1, PoderEntityTemplate.PODER_TEMPLATE_VALIDO);

        //when
        HeroiEntity heroiEntity = heroiMapper.converterEntradaParaEntity(heroiEntrada, poderes);
        //then
        Assert.assertNotNull(heroiEntity);
        Assert.assertEquals(heroiEntrada.getNome(), heroiEntity.getNome());
        Assert.assertEquals(heroiEntity.getPoderes().get(0).getDescricao(), poderes.get(0).getDescricao());
        Assert.assertEquals(heroiEntity.getPoderes().get(0).getId(), poderes.get(0).getId());
    }

    @Test
    public void deveConverterEntityParaSaida(){
        //given
        HeroiEntity heroiEntity = Fixture.from(HeroiEntity.class).gimme(HeroiEntityTemplate.HEROI_TEMPLATE_VALIDO);

        //when
        HeroiSaida heroiSaida = heroiMapper.converterEntityParaSaida(heroiEntity);

        //then
        Assert.assertNotNull(heroiSaida);
        Assert.assertEquals(heroiEntity.getId(), heroiSaida.getId());
        Assert.assertEquals(heroiEntity.getNome(), heroiSaida.getNome());
        Assert.assertEquals(heroiEntity.getPoderes().get(0).getDescricao(), heroiSaida.getPoderes().get(0).getDescricao());
        Assert.assertEquals(heroiEntity.getPoderes().get(0).getId(), heroiSaida.getPoderes().get(0).getId());
    }
}
