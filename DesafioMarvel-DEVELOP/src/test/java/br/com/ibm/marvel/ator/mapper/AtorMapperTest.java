package br.com.ibm.marvel.ator.mapper;

import br.com.ibm.marvel.ator.model.AtorEntity;
import br.com.ibm.marvel.ator.model.AtorEntrada;
import br.com.ibm.marvel.ator.model.AtorSaida;
import br.com.ibm.marvel.heroi.model.HeroiEntity;
import br.com.ibm.marvel.templates.AtorEntityTemplate;
import br.com.ibm.marvel.templates.AtorEntradaTemplate;
import br.com.ibm.marvel.templates.HeroiEntityTemplate;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class AtorMapperTest {
    private AtorMapper atorMapper = Mappers.getMapper(AtorMapper.class);

    @Before
    public void setup() {
        FixtureFactoryLoader.loadTemplates("br.com.ibm.marvel.templates");
    }

    @Test
    public void deveConverterEntradaParaEntity(){
        //given
        AtorEntrada atorEntrada = Fixture.from(AtorEntrada.class).gimme(AtorEntradaTemplate.ATOR_ENTRADA_VALIDO);
        HeroiEntity heroi = Fixture.from(HeroiEntity.class).gimme(HeroiEntityTemplate.HEROI_TEMPLATE_VALIDO);

        //when
        AtorEntity atorEntity = atorMapper.converterEntradaParaEntity(atorEntrada, heroi);

        //then
        Assert.assertNotNull(atorEntity);
        Assert.assertEquals(atorEntrada.getNomeAtor(), atorEntity.getNomeAtor());
        Assert.assertEquals(atorEntity.getHeroi().getNome(), heroi.getNome());
        Assert.assertEquals(atorEntity.getHeroi().getId(), heroi.getId());
        Assert.assertEquals(atorEntity.getHeroi().getPoderes().get(0).getDescricao(), heroi.getPoderes().get(0).getDescricao());
        Assert.assertEquals(atorEntity.getHeroi().getPoderes().get(0).getId(), heroi.getPoderes().get(0).getId());
    }

    @Test
    public void deveConverterClasseEntityParaSaida(){
        //given
        AtorEntity atorEntity = Fixture.from(AtorEntity.class).gimme(AtorEntityTemplate.ATOR_TEMPLATE_VALIDO);

        //when
        AtorSaida atorSaida = atorMapper.converterEntityParaSaida(atorEntity);

        //then
        Assert.assertNotNull(atorSaida);
        Assert.assertEquals(atorEntity.getId(), atorSaida.getId());
        Assert.assertEquals(atorEntity.getNomeAtor(), atorSaida.getNomeAtor());
        Assert.assertEquals(atorEntity.getHeroi().getNome(), atorSaida.getHeroi().getNome());
        Assert.assertEquals(atorEntity.getHeroi().getId(), atorSaida.getHeroi().getId());
        Assert.assertEquals(atorEntity.getHeroi().getPoderes().get(0).getDescricao(), atorSaida.getHeroi().getPoderes().get(0).getDescricao());
        Assert.assertEquals(atorEntity.getHeroi().getPoderes().get(0).getId(), atorSaida.getHeroi().getPoderes().get(0).getId());
    }


}
