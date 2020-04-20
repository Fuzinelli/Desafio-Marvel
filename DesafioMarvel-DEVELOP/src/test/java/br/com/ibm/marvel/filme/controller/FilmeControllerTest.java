package br.com.ibm.marvel.filme.controller;

import br.com.ibm.marvel.filme.facade.FilmeFacade;
import br.com.ibm.marvel.filme.model.FilmeEntrada;
import br.com.ibm.marvel.filme.model.FilmeSaida;
import br.com.ibm.marvel.templates.FilmeEntradaTemplate;
import br.com.ibm.marvel.templates.FilmeSaidaTemplate;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class FilmeControllerTest {
    @InjectMocks
    FilmeController filmeController;

    @Mock
    FilmeFacade filmeFacade;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(filmeController).build();
        FixtureFactoryLoader.loadTemplates("br.com.ibm.marvel.templates");
    }

    @Test
    public void deveListarFilmes() throws Exception {
        List<FilmeSaida> filmeSaida = new ArrayList<FilmeSaida>()
        {{add(Fixture.from(FilmeSaida.class).gimme(FilmeSaidaTemplate.FILME_SAIDA_VALIDO));}};

        FilmeEntrada filmeEntrada = Fixture.from(FilmeEntrada.class).gimme(FilmeEntradaTemplate.FILME_ENTRADA_VALIDO);

        Mockito.when(filmeFacade.listarFilmes()).thenReturn(filmeSaida);

        MvcResult result = mockMvc.perform(get("/marvel/filme")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();

        List<FilmeSaida> saida = Arrays.asList(new ObjectMapper().readValue(json, FilmeSaida[].class));

        Assert.assertNotNull(saida);
        Assert.assertEquals(filmeSaida.get(0).getNome(), saida.get(0).getNome());
        Assert.assertEquals(filmeSaida.get(0).getId(), saida.get(0).getId());
        Assert.assertEquals(filmeSaida.get(0).getAtores().get(0).getId(), saida.get(0).getAtores().get(0).getId());
        Assert.assertEquals(filmeSaida.get(0).getAtores().get(0).getNomeAtor(), saida.get(0).getAtores().get(0).getNomeAtor());
        Assert.assertEquals(filmeSaida.get(0).getAtores().get(0).getHeroi().getId(), saida.get(0).getAtores().get(0).getHeroi().getId());
        Assert.assertEquals(filmeSaida.get(0).getAtores().get(0).getHeroi().getNome(), saida.get(0).getAtores().get(0).getHeroi().getNome());
        Assert.assertEquals(filmeSaida.get(0).getAtores().get(0).getHeroi().getPoderes().get(0).getId(), saida.get(0).getAtores().get(0).getHeroi().getPoderes().get(0).getId());
        Assert.assertEquals(filmeSaida.get(0).getAtores().get(0).getHeroi().getPoderes().get(0).getDescricao(), saida.get(0).getAtores().get(0).getHeroi().getPoderes().get(0).getDescricao());
    }

    @Test

    public void deveCadastrarFilme() throws Exception {
        FilmeSaida filmeSaida = Fixture.from(FilmeSaida.class).gimme(FilmeSaidaTemplate.FILME_SAIDA_VALIDO);
        FilmeEntrada filmeEntrada = Fixture.from(FilmeEntrada.class).gimme(FilmeEntradaTemplate.FILME_ENTRADA_VALIDO);

        Mockito.when(filmeFacade.salvar(Mockito.any(FilmeEntrada.class))).thenReturn(filmeSaida);

        MvcResult result = mockMvc.perform(post("/marvel/filme")
                .content(asJsonString(filmeEntrada))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();

        FilmeSaida saida = new ObjectMapper().readValue(json, FilmeSaida.class);

        Assert.assertNotNull(saida);
        Assert.assertEquals(filmeSaida.getNome(), saida.getNome());
        Assert.assertEquals(filmeSaida.getId(), saida.getId());
        Assert.assertEquals(filmeSaida.getAtores().get(0).getId(), saida.getAtores().get(0).getId());
        Assert.assertEquals(filmeSaida.getAtores().get(0).getNomeAtor(), saida.getAtores().get(0).getNomeAtor());
        Assert.assertEquals(filmeSaida.getAtores().get(0).getHeroi().getId(), saida.getAtores().get(0).getHeroi().getId());
        Assert.assertEquals(filmeSaida.getAtores().get(0).getHeroi().getNome(), saida.getAtores().get(0).getHeroi().getNome());
        Assert.assertEquals(filmeSaida.getAtores().get(0).getHeroi().getPoderes().get(0).getId(), saida.getAtores().get(0).getHeroi().getPoderes().get(0).getId());
        Assert.assertEquals(filmeSaida.getAtores().get(0).getHeroi().getPoderes().get(0).getDescricao(), saida.getAtores().get(0).getHeroi().getPoderes().get(0).getDescricao());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
