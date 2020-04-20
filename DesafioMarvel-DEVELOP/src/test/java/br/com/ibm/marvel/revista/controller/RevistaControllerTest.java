package br.com.ibm.marvel.revista.controller;

import br.com.ibm.marvel.revista.facade.RevistaFacade;
import br.com.ibm.marvel.revista.model.RevistaEntrada;
import br.com.ibm.marvel.revista.model.RevistaSaida;
import br.com.ibm.marvel.templates.RevistaEntradaTemplate;
import br.com.ibm.marvel.templates.RevistaSaidaTemplate;
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
public class RevistaControllerTest {
    @InjectMocks
    RevistaController revistaController;

    @Mock
    RevistaFacade revistaFacade;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(revistaController).build();
        FixtureFactoryLoader.loadTemplates("br.com.ibm.marvel.templates");
    }

    @Test

    public void deveCadastrarRevista() throws Exception {
        RevistaSaida revistaSaida = Fixture.from(RevistaSaida.class).gimme(RevistaSaidaTemplate.REVISTA_SAIDA_VALIDO);
        RevistaEntrada revistaEntrada = Fixture.from(RevistaEntrada.class).gimme(RevistaEntradaTemplate.REVISTA_ENTRADA_VALIDO);

        Mockito.when(revistaFacade.salvarRevista(Mockito.any(RevistaEntrada.class))).thenReturn(revistaSaida);

        MvcResult result = mockMvc.perform(post("/marvel/revista")
                .content(asJsonString(revistaEntrada))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();

        RevistaSaida saida = new ObjectMapper().readValue(json, RevistaSaida.class);

        Assert.assertNotNull(saida);
        Assert.assertEquals(revistaSaida.getNome(), saida.getNome());
        Assert.assertEquals(revistaSaida.getId(), saida.getId());
        Assert.assertEquals(saida.getHerois().get(0).getId(), revistaSaida.getHerois().get(0).getId());
        Assert.assertEquals(saida.getHerois().get(0).getNome(), revistaSaida.getHerois().get(0).getNome());
        Assert.assertEquals(saida.getHerois().get(0).getPoderes().get(0).getId(), revistaSaida.getHerois().get(0).getPoderes().get(0).getId());
        Assert.assertEquals(saida.getHerois().get(0).getPoderes().get(0).getDescricao(), revistaSaida.getHerois().get(0).getPoderes().get(0).getDescricao());
    }

    @Test
    public void deveListarRevistas() throws Exception {
        List<RevistaSaida> revistaSaida = new ArrayList<RevistaSaida>()

        {{add(Fixture.from(RevistaSaida.class).gimme(RevistaSaidaTemplate.REVISTA_SAIDA_VALIDO));}};

        RevistaEntrada revistaEntrada = Fixture.from(RevistaEntrada.class).gimme(RevistaEntradaTemplate.REVISTA_ENTRADA_VALIDO);

        Mockito.when(revistaFacade.listarRevistas()).thenReturn(revistaSaida);

        MvcResult result = mockMvc.perform(get("/marvel/revista")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();

        List<RevistaSaida> saida = Arrays.asList(new ObjectMapper().readValue(json, RevistaSaida[].class));

        Assert.assertNotNull(saida);
        Assert.assertEquals(revistaSaida.get(0).getNome(), saida.get(0).getNome());
        Assert.assertEquals(revistaSaida.get(0).getId(), saida.get(0).getId());
        Assert.assertEquals(saida.get(0).getHerois().get(0).getId(), revistaSaida.get(0).getHerois().get(0).getId());
        Assert.assertEquals(saida.get(0).getHerois().get(0).getNome(), revistaSaida.get(0).getHerois().get(0).getNome());
        Assert.assertEquals(saida.get(0).getHerois().get(0).getPoderes().get(0).getId(), revistaSaida.get(0).getHerois().get(0).getPoderes().get(0).getId());
        Assert.assertEquals(saida.get(0).getHerois().get(0).getPoderes().get(0).getDescricao(), revistaSaida.get(0).getHerois().get(0).getPoderes().get(0).getDescricao());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
