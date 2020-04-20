package br.com.ibm.marvel.poder.controller;

import br.com.ibm.marvel.poder.facade.PoderFacade;
import br.com.ibm.marvel.poder.model.PoderEntity;
import br.com.ibm.marvel.poder.model.PoderEntrada;
import br.com.ibm.marvel.poder.model.PoderSaida;
import br.com.ibm.marvel.templates.PoderEntityTemplate;
import br.com.ibm.marvel.templates.PoderEntradaTemplate;
import br.com.ibm.marvel.templates.PoderSaidaTemplate;
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
public class PoderControllerTest {

    @InjectMocks
    private PoderController poderController;

    @Mock
    private PoderFacade poderFacade;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(poderController).build();
        FixtureFactoryLoader.loadTemplates("br.com.ibm.marvel.templates");
    }
    @Test
    public void deveListarPoderes() throws Exception{
        List<PoderSaida> poderes = new ArrayList<>();
        poderes.add(Fixture.from(PoderSaida.class).gimme( PoderSaidaTemplate.PODER_SAIDA_VALIDO));

        Mockito.when(poderFacade.listarPoderes()).thenReturn(poderes);
        MvcResult result = mockMvc.perform(get("/marvel/poder")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        List<PoderSaida> saida = Arrays.asList(new ObjectMapper().readValue(json, PoderSaida[].class));

        Assert.assertNotNull(poderes);
        Assert.assertEquals(poderes.get(0).getDescricao(), poderes.get(0).getDescricao());
        Assert.assertEquals(poderes.get(0).getId(), poderes.get(0).getId());
    }

    @Test
    public void deveSalvarPoder() throws Exception{
        PoderSaida poderSaida = Fixture.from(PoderSaida.class).gimme(PoderSaidaTemplate.PODER_SAIDA_VALIDO);

        PoderEntrada poderEntrada = Fixture.from(PoderEntrada.class).gimme(PoderEntradaTemplate.PODER_ENTRADA_VALIDO);

        Mockito.when(poderFacade.salvar(Mockito.any(PoderEntrada.class))).thenReturn(poderSaida);

        MvcResult result = mockMvc.perform(post("/marvel/poder")
                .content(asJsonString(poderEntrada))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();

        PoderSaida saida = new ObjectMapper().readValue(json, PoderSaida.class);

        Assert.assertNotNull(saida);
        Assert.assertEquals(saida.getDescricao(), poderEntrada.getDescricao());

    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
