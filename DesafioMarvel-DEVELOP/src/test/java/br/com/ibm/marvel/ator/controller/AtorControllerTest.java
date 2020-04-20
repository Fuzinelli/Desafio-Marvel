package br.com.ibm.marvel.ator.controller;

import br.com.ibm.marvel.ator.facade.AtorFacade;
import br.com.ibm.marvel.ator.model.AtorEntrada;
import br.com.ibm.marvel.ator.model.AtorSaida;
import br.com.ibm.marvel.templates.AtorEntradaTemplate;
import br.com.ibm.marvel.templates.AtorSaidaTemplate;
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
public class AtorControllerTest {
    @InjectMocks
    AtorController atorController;

    @Mock
    AtorFacade atorFacade;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(atorController).build();
        FixtureFactoryLoader.loadTemplates("br.com.ibm.marvel.templates");
    }

    @Test
    public void deveListarAtores() throws Exception {
        List<AtorSaida> atorSaida = new ArrayList<AtorSaida>()
        {{add(Fixture.from(AtorSaida.class).gimme(AtorSaidaTemplate.ATOR_SAIDA_VALIDO));}};

        AtorEntrada atorEntrada = Fixture.from(AtorEntrada.class).gimme(AtorEntradaTemplate.ATOR_ENTRADA_VALIDO);

        Mockito.when(atorFacade.listarAtores()).thenReturn(atorSaida);

        MvcResult result = mockMvc.perform(get("/marvel/ator")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();

        List<AtorSaida> saida = Arrays.asList(new ObjectMapper().readValue(json, AtorSaida[].class));

        Assert.assertNotNull(saida);
        Assert.assertEquals(atorSaida.get(0).getNomeAtor(), saida.get(0).getNomeAtor());
        Assert.assertEquals(atorSaida.get(0).getId(), saida.get(0).getId());
        Assert.assertEquals(atorSaida.get(0).getHeroi().getId(), saida.get(0).getHeroi().getId());
        Assert.assertEquals(atorSaida.get(0).getHeroi().getNome(), saida.get(0).getHeroi().getNome());
        Assert.assertEquals(atorSaida.get(0).getHeroi().getPoderes().get(0).getId(), saida.get(0).getHeroi().getPoderes().get(0).getId());
        Assert.assertEquals(atorSaida.get(0).getHeroi().getPoderes().get(0).getDescricao(), saida.get(0).getHeroi().getPoderes().get(0).getDescricao());
    }

    @Test

    public void deveCadastrarAtor() throws Exception {
        AtorSaida atorSaida = Fixture.from(AtorSaida.class).gimme(AtorSaidaTemplate.ATOR_SAIDA_VALIDO);
        AtorEntrada atorEntrada = Fixture.from(AtorEntrada.class).gimme(AtorEntradaTemplate.ATOR_ENTRADA_VALIDO);

        Mockito.when(atorFacade.salvar(Mockito.any(AtorEntrada.class))).thenReturn(atorSaida);

        MvcResult result = mockMvc.perform(post("/marvel/ator")
                .content(asJsonString(atorEntrada))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();

        AtorSaida saida = new ObjectMapper().readValue(json, AtorSaida.class);

        Assert.assertNotNull(saida);
        Assert.assertEquals(atorSaida.getNomeAtor(), saida.getNomeAtor());
        Assert.assertEquals(atorSaida.getId(), saida.getId());
        Assert.assertEquals(atorSaida.getHeroi().getId(), saida.getHeroi().getId());
        Assert.assertEquals(atorSaida.getHeroi().getNome(), saida.getHeroi().getNome());
        Assert.assertEquals(atorSaida.getHeroi().getPoderes().get(0).getId(), saida.getHeroi().getPoderes().get(0).getId());
        Assert.assertEquals(atorSaida.getHeroi().getPoderes().get(0).getDescricao(), saida.getHeroi().getPoderes().get(0).getDescricao());

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
