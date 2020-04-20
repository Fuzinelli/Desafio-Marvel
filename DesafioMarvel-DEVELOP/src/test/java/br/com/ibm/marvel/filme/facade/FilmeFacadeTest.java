package br.com.ibm.marvel.filme.facade;

import br.com.ibm.marvel.ator.model.AtorEntity;
import br.com.ibm.marvel.ator.model.AtorEntrada;
import br.com.ibm.marvel.ator.model.AtorSaida;
import br.com.ibm.marvel.ator.repository.AtorRepository;
import br.com.ibm.marvel.filme.model.FilmeEntity;
import br.com.ibm.marvel.filme.model.FilmeEntrada;
import br.com.ibm.marvel.filme.model.FilmeSaida;
import br.com.ibm.marvel.filme.repository.FilmeRepository;
import br.com.ibm.marvel.heroi.model.HeroiEntity;
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
public class FilmeFacadeTest {

    @InjectMocks
    FilmeFacade filmeFacade;

    @Mock
    FilmeRepository filmeRepository;

    @Mock
    AtorRepository atorRepository;


    @Before
    public void setup() {
        FixtureFactoryLoader.loadTemplates("br.com.ibm.marvel.templates");
    }

    @Test
    public void deveListarFilmes() throws Exception {
        //given
        List<FilmeEntity> filmeEntity = Fixture.from(FilmeEntity.class).gimme(1, FilmeEntityTemplate.FILME_TEMPLATE_VALIDO);

        Mockito.when(filmeRepository.findAll()).thenReturn(filmeEntity);
        //when
        List<FilmeSaida> filmeSaidas = filmeFacade.listarFilmes();

        //then
        Assert.assertNotNull(filmeSaidas);
    }

    @Test
    public void deveSalvarFilmes() throws Exception {
        FilmeEntrada filmeEntrada = Fixture.from(FilmeEntrada.class).gimme(FilmeEntradaTemplate.FILME_ENTRADA_VALIDO);

        FilmeEntity filmeEntity = Fixture.from(FilmeEntity.class).gimme(FilmeEntityTemplate.FILME_TEMPLATE_VALIDO);

        Mockito.when(filmeRepository.save(Mockito.anyObject())).thenReturn(filmeEntity);
        AtorEntity atorEntity = Fixture.from(AtorEntity.class).gimme(AtorEntityTemplate.ATOR_TEMPLATE_VALIDO);
        Mockito.when(atorRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(atorEntity));

        FilmeSaida filmeSaida = filmeFacade.salvar(filmeEntrada);

        Assert.assertNotNull(filmeSaida);
        Assert.assertEquals(filmeEntrada.getNome(), filmeEntity.getNome());
        Assert.assertEquals(filmeEntity.getNome(), filmeSaida.getNome());
        Assert.assertEquals(filmeEntrada.getNome(), filmeSaida.getNome());
        Assert.assertEquals(filmeSaida.getAtores().get(0).getNomeAtor(), atorEntity.getNomeAtor());
        Assert.assertEquals(filmeSaida.getAtores().get(0).getId(), atorEntity.getId());
        Assert.assertEquals(filmeSaida.getAtores().get(0).getHeroi().getNome(), atorEntity.getHeroi().getNome());
        Assert.assertEquals(filmeSaida.getAtores().get(0).getHeroi().getId(), atorEntity.getHeroi().getId());
        Assert.assertEquals(filmeSaida.getAtores().get(0).getHeroi().getPoderes().get(0).getDescricao(), atorEntity.getHeroi().getPoderes().get(0).getDescricao());
        Assert.assertEquals(filmeSaida.getAtores().get(0).getHeroi().getPoderes().get(0).getId(), atorEntity.getHeroi().getPoderes().get(0).getId());
    }

    @Test
    public void deveAlterarNomeFilme() throws Exception{
        Long id = 1L;
        FilmeEntrada filmeEntrada = Fixture.from(FilmeEntrada.class).gimme(FilmeEntradaTemplate.FILME_ENTRADA_VALIDO);

        FilmeEntity filmeEntity = Fixture.from(FilmeEntity.class).gimme(FilmeEntityTemplate.FILME_TEMPLATE_VALIDO);
        Mockito.when(filmeRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(filmeEntity));
        Mockito.when(filmeRepository.save(Mockito.anyObject())).thenReturn(filmeEntity);

        FilmeSaida filmeSaida = filmeFacade.alterarNome(id, filmeEntrada);
        Assert.assertEquals(filmeEntrada.getNome(), filmeSaida.getNome());


    }


}
