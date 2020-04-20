package br.com.ibm.marvel.ator.facade;

import br.com.ibm.marvel.ator.model.AtorEntity;
import br.com.ibm.marvel.ator.model.AtorEntrada;
import br.com.ibm.marvel.ator.model.AtorSaida;
import br.com.ibm.marvel.ator.repository.AtorRepository;
import br.com.ibm.marvel.heroi.model.HeroiEntity;
import br.com.ibm.marvel.heroi.repository.HeroiRepository;
import br.com.ibm.marvel.templates.AtorEntityTemplate;
import br.com.ibm.marvel.templates.AtorEntradaTemplate;
import br.com.ibm.marvel.templates.HeroiEntityTemplate;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
public class AtorFacadeTest {

    @InjectMocks
    AtorFacade atorFacade;

    @Mock
    AtorRepository atorRepository;

    @Mock
    HeroiRepository heroiRepository;

    @Before
    public void setup(){
        FixtureFactoryLoader.loadTemplates("br.com.ibm.marvel.templates");
    }

    @Test
    public void deveListarAtores() throws Exception {
        //given
        List<AtorEntity> atorEntity = Fixture.from(AtorEntity.class).gimme(1,AtorEntityTemplate.ATOR_TEMPLATE_VALIDO);

        Mockito.when(atorRepository.findAll()).thenReturn(atorEntity);
        //when
        List<AtorSaida> atorSaidas = atorFacade.listarAtores();

        //then
        Assert.assertNotNull(atorSaidas);
    }

    @Test
    public void deveSalvarAtores() throws Exception{
        AtorEntrada atorEntrada = Fixture.from(AtorEntrada.class).gimme(AtorEntradaTemplate.ATOR_ENTRADA_VALIDO);

        AtorEntity atorEntity = Fixture.from(AtorEntity.class).gimme(AtorEntityTemplate.ATOR_TEMPLATE_VALIDO);
        Mockito.when(atorRepository.save(Mockito.anyObject())).thenReturn(atorEntity);

        HeroiEntity heroiEntity = Fixture.from(HeroiEntity.class).gimme(HeroiEntityTemplate.HEROI_TEMPLATE_VALIDO);
        Mockito.when(heroiRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(heroiEntity));

        AtorSaida atorSaida = atorFacade.salvar(atorEntrada);

        Assert.assertNotNull(atorSaida);

    }

    @Test
    public void deveAlterarNomeAtor() throws Exception{
        Long id = 1L;
        AtorEntrada atorEntrada = Fixture.from(AtorEntrada.class).gimme(AtorEntradaTemplate.ATOR_ENTRADA_VALIDO);

        AtorEntity atorEntity = Fixture.from(AtorEntity.class).gimme(AtorEntityTemplate.ATOR_TEMPLATE_VALIDO);
        Mockito.when(atorRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(atorEntity));
        Mockito.when(atorRepository.save(Mockito.anyObject())).thenReturn(atorEntity);

        AtorSaida atorSaida = atorFacade.alterarNome(id,atorEntrada);
        Assert.assertEquals(atorEntrada.getNomeAtor(), atorSaida.getNomeAtor());
    }
}