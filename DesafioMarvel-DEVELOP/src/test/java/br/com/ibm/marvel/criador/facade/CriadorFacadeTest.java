package br.com.ibm.marvel.criador.facade;

import br.com.ibm.marvel.criador.model.CriadorEntity;
import br.com.ibm.marvel.criador.model.CriadorEntrada;
import br.com.ibm.marvel.criador.model.CriadorSaida;
import br.com.ibm.marvel.criador.repository.CriadorRepository;
import br.com.ibm.marvel.filme.model.FilmeEntity;
import br.com.ibm.marvel.filme.repository.FilmeRepository;
import br.com.ibm.marvel.heroi.model.HeroiEntity;
import br.com.ibm.marvel.heroi.repository.HeroiRepository;
import br.com.ibm.marvel.revista.model.RevistaEntity;
import br.com.ibm.marvel.revista.repository.RevistaRepository;
import br.com.ibm.marvel.templates.*;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
public class CriadorFacadeTest {
    @InjectMocks
    CriadorFacade criadorFacade;
    @Mock
    CriadorRepository criadorRepository;

    @Mock
    FilmeRepository filmeRepository;

    @Mock
    RevistaRepository revistaRepository;

    @Mock
    HeroiRepository heroiRepository;

    @Before
    public void setup(){
        FixtureFactoryLoader.loadTemplates("br.com.ibm.marvel.templates");
    }

    @Test
    public void deveListarCriadores() throws Exception {
        //given
        List<CriadorEntity> criadorEntity = Fixture.from(CriadorEntity.class).gimme(1, CriadorEntityTemplate.CRIADOR_ENTITY_VALIDO);

        Mockito.when(criadorRepository.findAll()).thenReturn(criadorEntity);
        //when
        List<CriadorSaida> criadorSaidas = criadorFacade.listarCriadores();

        //then
        Assert.assertNotNull(criadorSaidas);
    }

    @Test
    public void deveSalvarCriadores() throws Exception {
        CriadorEntrada criadorEntrada = Fixture.from(CriadorEntrada.class).gimme(CriadorEntradaTemplate.CRIADOR_ENTRADA_VALIDO);

        CriadorEntity criadorEntity = Fixture.from(CriadorEntity.class).gimme(CriadorEntityTemplate.CRIADOR_ENTITY_VALIDO);
        Mockito.when(criadorRepository.save(Mockito.anyObject())).thenReturn(criadorEntity);

        FilmeEntity filmeEntity = Fixture.from(FilmeEntity.class).gimme(FilmeEntityTemplate.FILME_TEMPLATE_VALIDO);

        Mockito.when(filmeRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(filmeEntity));
        RevistaEntity revistaEntity = Fixture.from(RevistaEntity.class).gimme(RevistaEntityTemplate.REVISTA_TEMPLATE_VALIDO);
        Mockito.when(revistaRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(revistaEntity));

        HeroiEntity heroiEntity = Fixture.from(HeroiEntity.class).gimme(HeroiEntityTemplate.HEROI_TEMPLATE_VALIDO);
        Mockito.when(heroiRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(heroiEntity));
        CriadorSaida criadorSaida = criadorFacade.salvar(criadorEntrada);

        Assert.assertNotNull(criadorSaida);
        Assert.assertEquals(criadorEntrada.getNome(), criadorEntity.getNome());
        Assert.assertEquals(criadorEntity.getNome(), criadorSaida.getNome());
        Assert.assertEquals(criadorEntity.getId(), criadorSaida.getId());
        Assert.assertEquals(criadorEntrada.getNome(), criadorSaida.getNome());
        Assert.assertEquals(criadorSaida.getFilmes().get(0).getNome(), filmeEntity.getNome());
        Assert.assertEquals(criadorSaida.getFilmes().get(0).getId(), filmeEntity.getId());
        Assert.assertEquals(criadorSaida.getFilmes().get(0).getAtores().get(0).getNomeAtor(), filmeEntity.getAtores().get(0).getNomeAtor());
        Assert.assertEquals(criadorSaida.getFilmes().get(0).getAtores().get(0).getId(), filmeEntity.getAtores().get(0).getId());
        Assert.assertEquals(criadorSaida.getFilmes().get(0).getAtores().get(0).getHeroi().getId(), filmeEntity.getAtores().get(0).getHeroi().getId());
        Assert.assertEquals(criadorSaida.getFilmes().get(0).getAtores().get(0).getHeroi().getNome(), filmeEntity.getAtores().get(0).getHeroi().getNome());
        Assert.assertEquals(criadorSaida.getFilmes().get(0).getAtores().get(0).getHeroi().getPoderes().get(0).getId(), filmeEntity.getAtores().get(0).getHeroi().getPoderes().get(0).getId());
        Assert.assertEquals(criadorSaida.getFilmes().get(0).getAtores().get(0).getHeroi().getPoderes().get(0).getDescricao(), filmeEntity.getAtores().get(0).getHeroi().getPoderes().get(0).getDescricao());
        Assert.assertEquals(criadorSaida.getHerois().get(0).getNome(), heroiEntity.getNome());
        Assert.assertEquals(criadorSaida.getHerois().get(0).getId(), heroiEntity.getId());
        Assert.assertEquals(criadorSaida.getHerois().get(0).getPoderes().get(0).getDescricao(), heroiEntity.getPoderes().get(0).getDescricao());
        Assert.assertEquals(criadorSaida.getHerois().get(0).getPoderes().get(0).getId(), heroiEntity.getPoderes().get(0).getId());
        Assert.assertEquals(criadorSaida.getRevistas().get(0).getNome(), revistaEntity.getNome());
        Assert.assertEquals(criadorSaida.getRevistas().get(0).getId(), revistaEntity.getId());
        Assert.assertEquals(criadorSaida.getRevistas().get(0).getHerois().get(0).getNome(), revistaEntity.getHerois().get(0).getNome());
        Assert.assertEquals(criadorSaida.getRevistas().get(0).getHerois().get(0).getId(), revistaEntity.getHerois().get(0).getId());
        Assert.assertEquals(criadorSaida.getRevistas().get(0).getHerois().get(0).getPoderes().get(0).getDescricao(), revistaEntity.getHerois().get(0).getPoderes().get(0).getDescricao());
        Assert.assertEquals(criadorSaida.getRevistas().get(0).getHerois().get(0).getPoderes().get(0).getId(), revistaEntity.getHerois().get(0).getPoderes().get(0).getId());
    }
    @Test
    public void deveAlterarNomeCriador() throws Exception{
        Long id = 1L;
        CriadorEntrada criadorEntrada = Fixture.from(CriadorEntrada.class).gimme(CriadorEntradaTemplate.CRIADOR_ENTRADA_VALIDO);

        CriadorEntity criadorEntity = Fixture.from(CriadorEntity.class).gimme(CriadorEntityTemplate.CRIADOR_ENTITY_VALIDO);
        Mockito.when(criadorRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(criadorEntity));
        Mockito.when(criadorRepository.save(Mockito.anyObject())).thenReturn(criadorEntity);

        CriadorSaida criadorSaida = criadorFacade.alterarNome(id, criadorEntrada);
        Assert.assertEquals(criadorEntrada.getNome(), criadorSaida.getNome());


    }
}
