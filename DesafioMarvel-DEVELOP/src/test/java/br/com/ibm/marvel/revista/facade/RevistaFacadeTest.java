package br.com.ibm.marvel.revista.facade;

import br.com.ibm.marvel.heroi.model.HeroiEntity;
import br.com.ibm.marvel.heroi.repository.HeroiRepository;
import br.com.ibm.marvel.revista.model.RevistaEntity;
import br.com.ibm.marvel.revista.model.RevistaEntrada;
import br.com.ibm.marvel.revista.model.RevistaSaida;
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
public class RevistaFacadeTest {

    @InjectMocks
    RevistaFacade revistaFacade;

    @Mock
    RevistaRepository revistaRepository;

    @Mock
    HeroiRepository heroiRepository;

    @Before
    public void setup(){
        FixtureFactoryLoader.loadTemplates("br.com.ibm.marvel.templates");
    }


    @Test
    public void deveListarRevistas() throws Exception {
        //given
        List<RevistaEntity> revistaEntity = Fixture.from(RevistaEntity.class).gimme(1, RevistaEntityTemplate.REVISTA_TEMPLATE_VALIDO);

        Mockito.when(revistaRepository.findAll()).thenReturn(revistaEntity);
        //when
        List<RevistaSaida> revistaSaidas = revistaFacade.listarRevistas();

        //then
        Assert.assertNotNull(revistaSaidas);
    }

    @Test
    public void deveSalvarRevistas() throws Exception {
        RevistaEntrada revistaEntrada = Fixture.from(RevistaEntrada.class).gimme(RevistaEntradaTemplate.REVISTA_ENTRADA_VALIDO);

        RevistaEntity revistaEntity = Fixture.from(RevistaEntity.class).gimme(RevistaEntityTemplate.REVISTA_TEMPLATE_VALIDO);
        Mockito.when(revistaRepository.save(Mockito.anyObject())).thenReturn(revistaEntity);

        HeroiEntity heroiEntity = Fixture.from(HeroiEntity.class).gimme(HeroiEntityTemplate.HEROI_TEMPLATE_VALIDO);
        Mockito.when(heroiRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(heroiEntity));

        RevistaSaida revistaSaida = revistaFacade.salvarRevista(revistaEntrada);

        Assert.assertNotNull(revistaSaida);
    }

    @Test
    public void deveAlterarNomeRevista() throws Exception{
        Long id = 1L;
        RevistaEntrada revistaEntrada = Fixture.from(RevistaEntrada.class).gimme(RevistaEntradaTemplate.REVISTA_ENTRADA_VALIDO);

        RevistaEntity revistaEntity = Fixture.from(RevistaEntity.class).gimme(RevistaEntityTemplate.REVISTA_TEMPLATE_VALIDO);
        Mockito.when(revistaRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(revistaEntity));
        Mockito.when(revistaRepository.save(Mockito.anyObject())).thenReturn(revistaEntity);

        RevistaSaida revistaSaida = revistaFacade.alterarNome(id, revistaEntrada);
        Assert.assertEquals(revistaEntrada.getNome(), revistaSaida.getNome());


    }

}
