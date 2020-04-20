package br.com.ibm.marvel.templates;

import br.com.ibm.marvel.ator.model.AtorEntity;
import br.com.ibm.marvel.ator.model.AtorEntrada;
import br.com.ibm.marvel.heroi.model.HeroiEntity;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class AtorEntradaTemplate implements TemplateLoader {

    public static final String ATOR_ENTRADA_VALIDO = "ator entrada valido";

    @Override
    public void load() {
        Fixture.of(AtorEntrada.class).addTemplate(ATOR_ENTRADA_VALIDO, new Rule() {{
            add("nomeAtor", "gustavo");
            add("heroi", (1L));
        }});
    }
}




