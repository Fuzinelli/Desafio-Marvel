package br.com.ibm.marvel.heroi.controller;

import br.com.ibm.marvel.heroi.facade.HeroiFacade;
import br.com.ibm.marvel.heroi.model.HeroiEntrada;
import br.com.ibm.marvel.heroi.model.HeroiSaida;
import br.com.ibm.marvel.templates.HeroiEntradaTemplate;
import br.com.ibm.marvel.templates.HeroiSaidaTemplate;
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
public class HeroiControllerTest {
    @InjectMocks
    HeroiController heroiController;

    @Mock
    HeroiFacade heroiFacade;

    private MockMvc mockMvc;

    @Before
    public void init(){
        mockMvc = MockMvcBuilders.standaloneSetup(heroiController).build();
        FixtureFactoryLoader.loadTemplates("br.com.ibm.marvel.templates");
    }

    @Test
    public void deveSalvarHeroi() throws Exception {
        HeroiSaida heroiSaida = Fixture.from(HeroiSaida.class).gimme(HeroiSaidaTemplate.HEROI_SAIDA_VALIDO);

        HeroiEntrada heroiEntrada = Fixture.from(HeroiEntrada.class).gimme(HeroiEntradaTemplate.HEROI_ENTRADA_VALIDO);
        Mockito.when(heroiFacade.salvarHeroi(Mockito.any(HeroiEntrada.class))).thenReturn(heroiSaida);
        MvcResult result = mockMvc.perform(post("/marvel/heroi")
                .content(asJsonString(heroiEntrada))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();

        HeroiSaida saida = new ObjectMapper().readValue(json, HeroiSaida.class);

        Assert.assertNotNull(saida);
        Assert.assertEquals(heroiSaida.getNome(), saida.getNome());
        Assert.assertEquals(saida.getId(), heroiSaida.getId());
        Assert.assertEquals(heroiSaida.getPoderes().get(0).getDescricao(), saida.getPoderes().get(0).getDescricao());
        Assert.assertEquals(heroiSaida.getPoderes().get(0).getId(), saida.getPoderes().get(0).getId());

    }

    @Test

    public void deveListarHerois() throws Exception {
        List<HeroiSaida> heroiSaida = new ArrayList<HeroiSaida>()

        {{add(Fixture.from(HeroiSaida.class).gimme(HeroiSaidaTemplate.HEROI_SAIDA_VALIDO));}};

        HeroiEntrada heroiEntrada = Fixture.from(HeroiEntrada.class).gimme(HeroiEntradaTemplate.HEROI_ENTRADA_VALIDO);

        Mockito.when(heroiFacade.listarHerois()).thenReturn(heroiSaida);


        MvcResult result = mockMvc.perform(get("/marvel/heroi")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();

        List<HeroiSaida> saida = Arrays.asList(new ObjectMapper().readValue(json, HeroiSaida[].class));

        Assert.assertNotNull(saida);
        Assert.assertEquals(heroiSaida.get(0).getNome(), saida.get(0).getNome());
        Assert.assertEquals(heroiSaida.get(0).getId(), saida.get(0).getId());
        Assert.assertEquals(saida.get(0).getPoderes().get(0).getId(), heroiSaida.get(0).getPoderes().get(0).getId());
        Assert.assertEquals(heroiSaida.get(0).getPoderes().get(0).getDescricao(), saida.get(0).getPoderes().get(0).getDescricao());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

