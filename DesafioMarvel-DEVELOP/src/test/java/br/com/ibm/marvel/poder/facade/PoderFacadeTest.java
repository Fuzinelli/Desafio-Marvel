package br.com.ibm.marvel.poder.facade;

import br.com.ibm.marvel.ator.model.AtorEntity;
import br.com.ibm.marvel.ator.model.AtorEntrada;
import br.com.ibm.marvel.ator.model.AtorSaida;
import br.com.ibm.marvel.poder.model.PoderEntity;
import br.com.ibm.marvel.poder.model.PoderEntrada;
import br.com.ibm.marvel.poder.model.PoderSaida;
import br.com.ibm.marvel.poder.repository.PoderRepository;
import br.com.ibm.marvel.templates.AtorEntityTemplate;
import br.com.ibm.marvel.templates.PoderEntityTemplate;
import br.com.ibm.marvel.templates.PoderEntradaTemplate;
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
public class PoderFacadeTest  {

    @InjectMocks
    PoderFacade poderFacade;

    @Mock
    PoderRepository poderRepository;

    @Before
    public void setup(){
        FixtureFactoryLoader.loadTemplates("br.com.ibm.marvel.templates");
    }


    @Test
    public void deveListarPoderes() throws Exception {
        //given
        List<PoderEntity> poderEntity = Fixture.from(PoderEntity.class).gimme(1, PoderEntityTemplate.PODER_TEMPLATE_VALIDO);

        Mockito.when(poderRepository.findAll()).thenReturn(poderEntity);
        //when
        List<PoderSaida> poderSaidas = poderFacade.listarPoderes();

        //then
        Assert.assertNotNull(poderSaidas);
    }

    @Test

    public void deveSalvarPoderes() throws Exception {

        PoderEntrada poderEntrada = Fixture.from(PoderEntrada.class).gimme(PoderEntradaTemplate.PODER_ENTRADA_VALIDO);

        PoderEntity poderEntity = Fixture.from(PoderEntity.class).gimme(PoderEntityTemplate.PODER_TEMPLATE_VALIDO);

        Mockito.when(poderRepository.save(Mockito.anyObject())).thenReturn(poderEntity);


        PoderSaida poderSaida = poderFacade.salvar(poderEntrada);

        Assert.assertNotNull(poderSaida);
        Assert.assertEquals(poderEntrada.getDescricao(), poderSaida.getDescricao());
        Assert.assertEquals(poderEntrada.getDescricao(), poderEntity.getDescricao());
        Assert.assertEquals(poderSaida.getDescricao(), poderEntity.getDescricao());
        Assert.assertEquals(poderSaida.getId(), poderEntity.getId());

    }

    @Test
    public void deveAlterarPoder() throws Exception{

        Long id = 1L;
        PoderEntrada poderEntrada = Fixture.from(PoderEntrada.class).gimme(PoderEntradaTemplate.PODER_ENTRADA_VALIDO);

        PoderEntity poderEntity = Fixture.from(PoderEntity.class).gimme(PoderEntityTemplate.PODER_TEMPLATE_VALIDO);
        Mockito.when(poderRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(poderEntity));
        Mockito.when(poderRepository.save(Mockito.anyObject())).thenReturn(poderEntity);

        PoderSaida poderSaida = poderFacade.alterarDescricao(id, poderEntrada);

        Assert.assertEquals(poderEntrada.getDescricao(), poderSaida.getDescricao());
    }

}
