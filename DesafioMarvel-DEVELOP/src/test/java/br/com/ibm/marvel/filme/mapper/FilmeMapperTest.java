package br.com.ibm.marvel.filme.mapper;

import br.com.ibm.marvel.ator.model.AtorEntity;
import br.com.ibm.marvel.filme.model.FilmeEntity;
import br.com.ibm.marvel.filme.model.FilmeEntrada;
import br.com.ibm.marvel.filme.model.FilmeSaida;
import br.com.ibm.marvel.templates.AtorEntityTemplate;
import br.com.ibm.marvel.templates.FilmeEntityTemplate;
import br.com.ibm.marvel.templates.FilmeEntradaTemplate;
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
public class FilmeMapperTest {
    FilmeMapper filmeMapper = Mappers.getMapper(FilmeMapper.class);

    @Before
    public void setup() {
        FixtureFactoryLoader.loadTemplates("br.com.ibm.marvel.templates");
    }

    @Test
    public void deveConverterEntradaParaEntity(){
        //given
        FilmeEntrada filmeEntrada = Fixture.from(FilmeEntrada.class).gimme(FilmeEntradaTemplate.FILME_ENTRADA_VALIDO);
        List<AtorEntity> atores = Fixture.from(AtorEntity.class).gimme(1, AtorEntityTemplate.ATOR_TEMPLATE_VALIDO);

        //when
        FilmeEntity filmeEntity = filmeMapper.converterEntradaParaEntity(filmeEntrada, atores);

        //then
        Assert.assertNotNull(filmeEntity);
        Assert.assertEquals(filmeEntrada.getNome(), filmeEntity.getNome());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getNomeAtor(), atores.get(0).getNomeAtor());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getHeroi().getNome(), atores.get(0).getHeroi().getNome());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getHeroi().getId(), atores.get(0).getHeroi().getId());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getHeroi().getPoderes().get(0).getDescricao(), atores.get(0).getHeroi().getPoderes().get(0).getDescricao());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getHeroi().getPoderes().get(0).getId(), atores.get(0).getHeroi().getPoderes().get(0).getId());
    }

    @Test
    public void deveConverterClasseEntityParaSaida(){
        //given
        FilmeEntity filmeEntity = Fixture.from(FilmeEntity.class).gimme(FilmeEntityTemplate.FILME_TEMPLATE_VALIDO);

        //when
        FilmeSaida filmeSaida = filmeMapper.converterEntityParaSaida(filmeEntity);

        //then
        Assert.assertNotNull(filmeSaida);
        Assert.assertEquals(filmeEntity.getId(), filmeSaida.getId());
        Assert.assertEquals(filmeEntity.getNome(), filmeSaida.getNome());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getNomeAtor(), filmeSaida.getAtores().get(0).getNomeAtor());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getHeroi().getNome(), filmeSaida.getAtores().get(0).getHeroi().getNome());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getHeroi().getId(), filmeSaida.getAtores().get(0).getHeroi().getId());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getHeroi().getPoderes().get(0).getDescricao(), filmeSaida.getAtores().get(0).getHeroi().getPoderes().get(0).getDescricao());
        Assert.assertEquals(filmeEntity.getAtores().get(0).getHeroi().getPoderes().get(0).getId(), filmeSaida.getAtores().get(0).getHeroi().getPoderes().get(0).getId());
    }
}
