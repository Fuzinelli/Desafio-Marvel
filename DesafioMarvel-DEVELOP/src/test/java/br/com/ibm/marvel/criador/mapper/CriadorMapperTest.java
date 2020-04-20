package br.com.ibm.marvel.criador.mapper;

import br.com.ibm.marvel.criador.model.CriadorEntity;
import br.com.ibm.marvel.criador.model.CriadorEntrada;
import br.com.ibm.marvel.criador.model.CriadorSaida;
import br.com.ibm.marvel.filme.model.FilmeEntity;
import br.com.ibm.marvel.heroi.model.HeroiEntity;
import br.com.ibm.marvel.revista.model.RevistaEntity;
import br.com.ibm.marvel.templates.*;
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
public class CriadorMapperTest {
    private CriadorMapper criadorMapper = Mappers.getMapper(CriadorMapper.class);

    @Before
    public void setup() {
        FixtureFactoryLoader.loadTemplates("br.com.ibm.marvel.templates");
    }

    @Test
    public void deveConverterEntradaParaEntity() {
        CriadorEntrada criadorEntrada = Fixture.from(CriadorEntrada.class).gimme(CriadorEntradaTemplate.CRIADOR_ENTRADA_VALIDO);
        List<FilmeEntity> filmes = Fixture.from(FilmeEntity.class).gimme(1, FilmeEntityTemplate.FILME_TEMPLATE_VALIDO);
        List<RevistaEntity> revistas = Fixture.from(RevistaEntity.class).gimme(1, RevistaEntityTemplate.REVISTA_TEMPLATE_VALIDO);
        List<HeroiEntity> herois = Fixture.from(HeroiEntity.class).gimme(1, HeroiEntityTemplate.HEROI_TEMPLATE_VALIDO);

        CriadorEntity criadorEntity = criadorMapper.converterEntradaParaEntity(criadorEntrada, herois, filmes, revistas);

        //then
        Assert.assertNotNull(criadorEntity);
        Assert.assertEquals(criadorEntrada.getNome(), criadorEntity.getNome());
        Assert.assertEquals(criadorEntity.getHerois().get(0).getNome(), herois.get(0).getNome());
        Assert.assertEquals(criadorEntity.getHerois().get(0).getId(), herois.get(0).getId());
        Assert.assertEquals(criadorEntity.getHerois().get(0).getPoderes().get(0).getDescricao(), herois.get(0).getPoderes().get(0).getDescricao());
        Assert.assertEquals(criadorEntity.getHerois().get(0).getPoderes().get(0).getId(), herois.get(0).getPoderes().get(0).getId());
        Assert.assertEquals(criadorEntity.getFilmes().get(0).getNome(), filmes.get(0).getNome());
        Assert.assertEquals(criadorEntity.getFilmes().get(0).getId(), filmes.get(0).getId());
        Assert.assertEquals(criadorEntity.getFilmes().get(0).getAtores().get(0).getNomeAtor(), filmes.get(0).getAtores().get(0).getNomeAtor());
        Assert.assertEquals(criadorEntity.getFilmes().get(0).getAtores().get(0).getId(), filmes.get(0).getAtores().get(0).getId());
        Assert.assertEquals(criadorEntity.getFilmes().get(0).getAtores().get(0).getHeroi().getNome(), filmes.get(0).getAtores().get(0).getHeroi().getNome());
        Assert.assertEquals(criadorEntity.getFilmes().get(0).getAtores().get(0).getHeroi().getId(), filmes.get(0).getAtores().get(0).getHeroi().getId());
        Assert.assertEquals(criadorEntity.getFilmes().get(0).getAtores().get(0).getHeroi().getPoderes().get(0).getDescricao(), filmes.get(0).getAtores().get(0).getHeroi().getPoderes().get(0).getDescricao());
        Assert.assertEquals(criadorEntity.getFilmes().get(0).getAtores().get(0).getHeroi().getPoderes().get(0).getId(), filmes.get(0).getAtores().get(0).getHeroi().getPoderes().get(0).getId());
        Assert.assertEquals(criadorEntity.getRevistas().get(0).getNome(), revistas.get(0).getNome());
        Assert.assertEquals(criadorEntity.getRevistas().get(0).getId(), revistas.get(0).getId());
        Assert.assertEquals(criadorEntity.getRevistas().get(0).getHerois().get(0).getNome(), revistas.get(0).getHerois().get(0).getNome());
        Assert.assertEquals(criadorEntity.getRevistas().get(0).getHerois().get(0).getId(), revistas.get(0).getHerois().get(0).getId());
        Assert.assertEquals(criadorEntity.getRevistas().get(0).getHerois().get(0).getPoderes().get(0).getDescricao(), revistas.get(0).getHerois().get(0).getPoderes().get(0).getDescricao());
        Assert.assertEquals(criadorEntity.getRevistas().get(0).getHerois().get(0).getPoderes().get(0).getId(), revistas.get(0).getHerois().get(0).getPoderes().get(0).getId());
    }

    @Test
    public void deveConverterEntityParaSaida() {
        CriadorEntity criadorEntity = Fixture.from(CriadorEntity.class).gimme(CriadorEntityTemplate.CRIADOR_ENTITY_VALIDO);

        CriadorSaida criadorSaida = criadorMapper.converterEntityParaSaida(criadorEntity);

        Assert.assertNotNull(criadorSaida);
        Assert.assertEquals(criadorEntity.getId(), criadorSaida.getId());
        Assert.assertEquals(criadorEntity.getNome(), criadorSaida.getNome());
        Assert.assertEquals(criadorEntity.getHerois().get(0).getNome(), criadorSaida.getHerois().get(0).getNome());
        Assert.assertEquals(criadorEntity.getHerois().get(0).getId(), criadorSaida.getHerois().get(0).getId());
        Assert.assertEquals(criadorEntity.getHerois().get(0).getPoderes().get(0).getDescricao(), criadorSaida.getHerois().get(0).getPoderes().get(0).getDescricao());
        Assert.assertEquals(criadorEntity.getHerois().get(0).getPoderes().get(0).getId(), criadorSaida.getHerois().get(0).getPoderes().get(0).getId());
        Assert.assertEquals(criadorEntity.getFilmes().get(0).getNome(), criadorSaida.getFilmes().get(0).getNome());
        Assert.assertEquals(criadorEntity.getFilmes().get(0).getId(), criadorSaida.getFilmes().get(0).getId());
        Assert.assertEquals(criadorEntity.getFilmes().get(0).getAtores().get(0).getNomeAtor(), criadorSaida.getFilmes().get(0).getAtores().get(0).getNomeAtor());
        Assert.assertEquals(criadorEntity.getFilmes().get(0).getAtores().get(0).getId(), criadorSaida.getFilmes().get(0).getAtores().get(0).getId());
        Assert.assertEquals(criadorEntity.getFilmes().get(0).getAtores().get(0).getHeroi().getNome(), criadorSaida.getFilmes().get(0).getAtores().get(0).getHeroi().getNome());
        Assert.assertEquals(criadorEntity.getFilmes().get(0).getAtores().get(0).getHeroi().getId(), criadorSaida.getFilmes().get(0).getAtores().get(0).getHeroi().getId());
        Assert.assertEquals(criadorEntity.getFilmes().get(0).getAtores().get(0).getHeroi().getPoderes().get(0).getDescricao(), criadorSaida.getFilmes().get(0).getAtores().get(0).getHeroi().getPoderes().get(0).getDescricao());
        Assert.assertEquals(criadorEntity.getFilmes().get(0).getAtores().get(0).getHeroi().getPoderes().get(0).getId(), criadorSaida.getFilmes().get(0).getAtores().get(0).getHeroi().getPoderes().get(0).getId());
        Assert.assertEquals(criadorEntity.getRevistas().get(0).getNome(), criadorSaida.getRevistas().get(0).getNome());
        Assert.assertEquals(criadorEntity.getRevistas().get(0).getId(), criadorSaida.getRevistas().get(0).getId());
        Assert.assertEquals(criadorEntity.getRevistas().get(0).getHerois().get(0).getNome(), criadorSaida.getRevistas().get(0).getHerois().get(0).getNome());
        Assert.assertEquals(criadorEntity.getRevistas().get(0).getHerois().get(0).getId(), criadorSaida.getRevistas().get(0).getHerois().get(0).getId());
        Assert.assertEquals(criadorEntity.getRevistas().get(0).getHerois().get(0).getPoderes().get(0).getDescricao(), criadorSaida.getRevistas().get(0).getHerois().get(0).getPoderes().get(0).getDescricao());
        Assert.assertEquals(criadorEntity.getRevistas().get(0).getHerois().get(0).getPoderes().get(0).getId(), criadorSaida.getRevistas().get(0).getHerois().get(0).getPoderes().get(0).getId());
    }
}
