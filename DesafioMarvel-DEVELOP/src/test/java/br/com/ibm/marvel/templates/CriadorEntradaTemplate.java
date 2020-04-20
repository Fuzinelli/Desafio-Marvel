package br.com.ibm.marvel.templates;

import br.com.ibm.marvel.criador.model.CriadorEntity;
import br.com.ibm.marvel.criador.model.CriadorEntrada;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.util.ArrayList;

public class CriadorEntradaTemplate implements TemplateLoader {
    public static final String CRIADOR_ENTRADA_VALIDO = "criador entrada valido";
    @Override
    public void load() {
        ArrayList<Long> filmes = new ArrayList<Long>(){{add(1L);}};
        ArrayList<Long> revistas = new ArrayList<Long>(){{add(1L);}};
        ArrayList<Long> herois = new ArrayList<Long>(){{add(1L);}};
        Fixture.of(CriadorEntrada.class).addTemplate(CRIADOR_ENTRADA_VALIDO, new Rule(){{
            add("nome", "gustavo");
            add("filmes", filmes);
            add("revistas", revistas);
            add("herois", herois);
        }});
    }
}
