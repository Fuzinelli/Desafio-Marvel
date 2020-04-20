package br.com.ibm.marvel.heroi.facade;

import br.com.ibm.marvel.ator.model.AtorEntity;
import br.com.ibm.marvel.ator.model.AtorSaida;
import br.com.ibm.marvel.heroi.model.HeroiEntity;
import br.com.ibm.marvel.heroi.model.HeroiEntrada;
import br.com.ibm.marvel.heroi.model.HeroiSaida;
import br.com.ibm.marvel.heroi.repository.HeroiRepository;
import br.com.ibm.marvel.poder.facade.PoderFacade;
import br.com.ibm.marvel.poder.model.PoderEntity;
import br.com.ibm.marvel.poder.repository.PoderRepository;
import br.com.ibm.marvel.templates.AtorEntityTemplate;
import br.com.ibm.marvel.templates.HeroiEntityTemplate;
import br.com.ibm.marvel.templates.HeroiEntradaTemplate;
import br.com.ibm.marvel.templates.PoderEntityTemplate;
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
public class HeroiFacadeTest {

    @InjectMocks
    HeroiFacade heroiFacade;

    @Mock
    HeroiRepository heroiRepository;

    @Mock
    PoderFacade poderFacade;

    @Before
    public void setup() {
        FixtureFactoryLoader.loadTemplates("br.com.ibm.marvel.templates");
    }

    @Test
    public void deveListarHerois() throws Exception {
        //given
        List<HeroiEntity> heroiEntity = Fixture.from(HeroiEntity.class).gimme(1, HeroiEntityTemplate.HEROI_TEMPLATE_VALIDO);

        Mockito.when(heroiRepository.findAll()).thenReturn(heroiEntity);
        //when
        List<HeroiSaida> heroiSaidas = heroiFacade.listarHerois();

        //then
        Assert.assertNotNull(heroiSaidas);
    }

    @Test
    public void deveSalvarHerois() throws Exception {
        HeroiEntrada heroiEntrada = Fixture.from(HeroiEntrada.class).gimme(HeroiEntradaTemplate.HEROI_ENTRADA_VALIDO);

        HeroiEntity heroiEntity = Fixture.from(HeroiEntity.class).gimme(HeroiEntityTemplate.HEROI_TEMPLATE_VALIDO);

        Mockito.when(heroiRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(heroiEntity));
        List<PoderEntity> poderes = Fixture.from(PoderEntity.class).gimme(1,PoderEntityTemplate.PODER_TEMPLATE_VALIDO);
        Mockito.when(poderFacade.buscarListaPoderesEntityPorId(Mockito.anyObject())).thenReturn((poderes));
        Mockito.when(heroiRepository.save(Mockito.anyObject())).thenReturn(heroiEntity);

        HeroiSaida heroiSaida = heroiFacade.salvarHeroi(heroiEntrada);

        Assert.assertNotNull(heroiSaida);
    }

    @Test
    public void deveAlterarNomeHeroi() throws Exception{
        Long id = 1L;
        HeroiEntrada heroiEntrada = Fixture.from(HeroiEntrada.class).gimme(HeroiEntradaTemplate.HEROI_ENTRADA_VALIDO);

        HeroiEntity heroiEntity = Fixture.from(HeroiEntity.class).gimme(HeroiEntityTemplate.HEROI_TEMPLATE_VALIDO);
        Mockito.when(heroiRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(heroiEntity));
        Mockito.when(heroiRepository.save(Mockito.anyObject())).thenReturn(heroiEntity);

        HeroiSaida heroiSaida = heroiFacade.alterarNome(id, heroiEntrada);
        Assert.assertEquals(heroiEntrada.getNome(), heroiSaida.getNome());


    }

}
