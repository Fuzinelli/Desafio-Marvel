package br.com.ibm.marvel.criador.controller;

import br.com.ibm.marvel.criador.facade.CriadorFacade;
import br.com.ibm.marvel.criador.model.CriadorEntrada;
import br.com.ibm.marvel.criador.model.CriadorSaida;
import br.com.ibm.marvel.templates.CriadorEntradaTemplate;
import br.com.ibm.marvel.templates.CriadorSaidaTemplate;
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
public class CriadorControllerTest {
    @InjectMocks
    CriadorController criadorController;

    @Mock
    CriadorFacade criadorFacade;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(criadorController).build();
        FixtureFactoryLoader.loadTemplates("br.com.ibm.marvel.templates");
    }

    @Test
    public void deveListarCriadores() throws Exception {
        List<CriadorSaida> criadorSaida = new ArrayList<CriadorSaida>()
        {{add(Fixture.from(CriadorSaida.class).gimme(CriadorSaidaTemplate.CRIADOR_SAIDA_VALIDO));}};

        CriadorEntrada criadorEntrada = Fixture.from(CriadorEntrada.class).gimme(CriadorEntradaTemplate.CRIADOR_ENTRADA_VALIDO);

        Mockito.when(criadorFacade.listarCriadores()).thenReturn(criadorSaida);

        MvcResult result = mockMvc.perform(get("/marvel/criador")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();

        List<CriadorSaida> saida = Arrays.asList(new ObjectMapper().readValue(json, CriadorSaida[].class));

        Assert.assertNotNull(saida);
        Assert.assertEquals(criadorSaida.get(0).getNome(), saida.get(0).getNome());
        Assert.assertEquals(criadorSaida.get(0).getId(), saida.get(0).getId());
        Assert.assertEquals(saida.get(0).getFilmes().get(0).getId(), criadorSaida.get(0).getFilmes().get(0).getId());
        Assert.assertEquals(saida.get(0).getFilmes().get(0).getNome(), criadorSaida.get(0).getFilmes().get(0).getNome());
        Assert.assertEquals(saida.get(0).getFilmes().get(0).getAtores().get(0).getId(), criadorSaida.get(0).getFilmes().get(0).getAtores().get(0).getId());
        Assert.assertEquals(saida.get(0).getFilmes().get(0).getAtores().get(0).getNomeAtor(), criadorSaida.get(0).getFilmes().get(0).getAtores().get(0).getNomeAtor());
        Assert.assertEquals(saida.get(0).getFilmes().get(0).getAtores().get(0).getHeroi().getId(), criadorSaida.get(0).getFilmes().get(0).getAtores().get(0).getHeroi().getId());
        Assert.assertEquals(saida.get(0).getFilmes().get(0).getAtores().get(0).getHeroi().getNome(), criadorSaida.get(0).getFilmes().get(0).getAtores().get(0).getHeroi().getNome());
        Assert.assertEquals(saida.get(0).getFilmes().get(0).getAtores().get(0).getHeroi().getPoderes().get(0).getId(), criadorSaida.get(0).getFilmes().get(0).getAtores().get(0).getHeroi().getPoderes().get(0).getId());
        Assert.assertEquals(saida.get(0).getFilmes().get(0).getAtores().get(0).getHeroi().getPoderes().get(0).getDescricao(), criadorSaida.get(0).getFilmes().get(0).getAtores().get(0).getHeroi().getPoderes().get(0).getDescricao());
        Assert.assertEquals(saida.get(0).getRevistas().get(0).getId(), criadorSaida.get(0).getRevistas().get(0).getId());
        Assert.assertEquals(saida.get(0).getRevistas().get(0).getNome(), criadorSaida.get(0).getRevistas().get(0).getNome());
        Assert.assertEquals(saida.get(0).getRevistas().get(0).getHerois().get(0).getId(), criadorSaida.get(0).getRevistas().get(0).getHerois().get(0).getId());
        Assert.assertEquals(saida.get(0).getRevistas().get(0).getHerois().get(0).getNome(), criadorSaida.get(0).getRevistas().get(0).getHerois().get(0).getNome());
        Assert.assertEquals(saida.get(0).getRevistas().get(0).getHerois().get(0).getPoderes().get(0).getId(), criadorSaida.get(0).getRevistas().get(0).getHerois().get(0).getPoderes().get(0).getId());
        Assert.assertEquals(saida.get(0).getRevistas().get(0).getHerois().get(0).getPoderes().get(0).getDescricao(), criadorSaida.get(0).getRevistas().get(0).getHerois().get(0).getPoderes().get(0).getDescricao());
        Assert.assertEquals(saida.get(0).getHerois().get(0).getId(), criadorSaida.get(0).getHerois().get(0).getId());
        Assert.assertEquals(saida.get(0).getHerois().get(0).getNome(), criadorSaida.get(0).getHerois().get(0).getNome());
        Assert.assertEquals(saida.get(0).getHerois().get(0).getPoderes().get(0).getId(), criadorSaida.get(0).getHerois().get(0).getPoderes().get(0).getId());
        Assert.assertEquals(saida.get(0).getHerois().get(0).getPoderes().get(0).getDescricao(), criadorSaida.get(0).getHerois().get(0).getPoderes().get(0).getDescricao());
    }

    @Test

    public void deveCadastrarCriador() throws Exception {
        CriadorSaida criadorSaida = Fixture.from(CriadorSaida.class).gimme(CriadorSaidaTemplate.CRIADOR_SAIDA_VALIDO);
        CriadorEntrada criadorEntrada = Fixture.from(CriadorEntrada.class).gimme(CriadorEntradaTemplate.CRIADOR_ENTRADA_VALIDO);

        Mockito.when(criadorFacade.salvar(Mockito.any(CriadorEntrada.class))).thenReturn(criadorSaida);

        MvcResult result = mockMvc.perform(post("/marvel/criador")
                .content(asJsonString(criadorEntrada))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();

        CriadorSaida saida = new ObjectMapper().readValue(json, CriadorSaida.class);

        Assert.assertNotNull(saida);
        Assert.assertEquals(criadorSaida.getNome(), saida.getNome());
        Assert.assertEquals(criadorSaida.getId(), saida.getId());
        Assert.assertEquals(saida.getFilmes().get(0).getId(), criadorSaida.getFilmes().get(0).getId());
        Assert.assertEquals(saida.getFilmes().get(0).getNome(), criadorSaida.getFilmes().get(0).getNome());
        Assert.assertEquals(saida.getFilmes().get(0).getAtores().get(0).getId(), criadorSaida.getFilmes().get(0).getAtores().get(0).getId());
        Assert.assertEquals(saida.getFilmes().get(0).getAtores().get(0).getNomeAtor(), criadorSaida.getFilmes().get(0).getAtores().get(0).getNomeAtor());
        Assert.assertEquals(saida.getFilmes().get(0).getAtores().get(0).getHeroi().getId(), criadorSaida.getFilmes().get(0).getAtores().get(0).getHeroi().getId());
        Assert.assertEquals(saida.getFilmes().get(0).getAtores().get(0).getHeroi().getNome(), criadorSaida.getFilmes().get(0).getAtores().get(0).getHeroi().getNome());
        Assert.assertEquals(saida.getFilmes().get(0).getAtores().get(0).getHeroi().getPoderes().get(0).getId(), criadorSaida.getFilmes().get(0).getAtores().get(0).getHeroi().getPoderes().get(0).getId());
        Assert.assertEquals(saida.getFilmes().get(0).getAtores().get(0).getHeroi().getPoderes().get(0).getDescricao(), criadorSaida.getFilmes().get(0).getAtores().get(0).getHeroi().getPoderes().get(0).getDescricao());
        Assert.assertEquals(saida.getRevistas().get(0).getId(), criadorSaida.getRevistas().get(0).getId());
        Assert.assertEquals(saida.getRevistas().get(0).getNome(), criadorSaida.getRevistas().get(0).getNome());
        Assert.assertEquals(saida.getRevistas().get(0).getHerois().get(0).getId(), criadorSaida.getRevistas().get(0).getHerois().get(0).getId());
        Assert.assertEquals(saida.getRevistas().get(0).getHerois().get(0).getNome(), criadorSaida.getRevistas().get(0).getHerois().get(0).getNome());
        Assert.assertEquals(saida.getRevistas().get(0).getHerois().get(0).getPoderes().get(0).getId(), criadorSaida.getRevistas().get(0).getHerois().get(0).getPoderes().get(0).getId());
        Assert.assertEquals(saida.getRevistas().get(0).getHerois().get(0).getPoderes().get(0).getDescricao(), criadorSaida.getRevistas().get(0).getHerois().get(0).getPoderes().get(0).getDescricao());
        Assert.assertEquals(saida.getHerois().get(0).getId(), criadorSaida.getHerois().get(0).getId());
        Assert.assertEquals(saida.getHerois().get(0).getNome(), criadorSaida.getHerois().get(0).getNome());
        Assert.assertEquals(saida.getHerois().get(0).getPoderes().get(0).getId(), criadorSaida.getHerois().get(0).getPoderes().get(0).getId());
        Assert.assertEquals(saida.getHerois().get(0).getPoderes().get(0).getDescricao(), criadorSaida.getHerois().get(0).getPoderes().get(0).getDescricao());
    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
