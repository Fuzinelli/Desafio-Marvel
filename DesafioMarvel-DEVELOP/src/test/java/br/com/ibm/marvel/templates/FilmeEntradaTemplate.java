package br.com.ibm.marvel.templates;

import br.com.ibm.marvel.ator.model.AtorEntity;
import br.com.ibm.marvel.filme.model.FilmeEntrada;
import br.com.ibm.marvel.heroi.model.HeroiEntrada;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.util.ArrayList;

public class FilmeEntradaTemplate  implements TemplateLoader {

    public static final String FILME_ENTRADA_VALIDO = "filme entrada valido";

    @Override
    public void load() {
        ArrayList<Long> atores = new ArrayList<>();
        atores.add(1L);
        Fixture.of(FilmeEntrada.class).addTemplate(FILME_ENTRADA_VALIDO, new Rule() {{
            add("nome", "marvel");
            add("atores", atores);
        }});

    }
}
