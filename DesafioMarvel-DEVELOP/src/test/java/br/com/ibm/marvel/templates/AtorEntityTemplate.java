package br.com.ibm.marvel.templates;

import br.com.ibm.marvel.ator.model.AtorEntity;
import br.com.ibm.marvel.filme.model.FilmeEntity;
import br.com.ibm.marvel.heroi.model.HeroiEntity;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class AtorEntityTemplate implements TemplateLoader {

    public static final String ATOR_TEMPLATE_VALIDO = "ator template valido";

    @Override
    public void load() {
        Fixture.of(AtorEntity.class).addTemplate(ATOR_TEMPLATE_VALIDO, new Rule() {{
            add("id", 1L);
            add("nomeAtor", "gustavo");
            add("heroi", one(HeroiEntity.class, HeroiEntityTemplate.HEROI_TEMPLATE_VALIDO));
        }});
    }
}

