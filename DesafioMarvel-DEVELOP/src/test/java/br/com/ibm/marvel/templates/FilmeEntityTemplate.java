package br.com.ibm.marvel.templates;

import br.com.ibm.marvel.ator.model.AtorEntity;
import br.com.ibm.marvel.filme.model.FilmeEntity;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class FilmeEntityTemplate implements TemplateLoader {

    public static final String FILME_TEMPLATE_VALIDO = "FILME template valido";

    @Override
    public void load() {
        Fixture.of(FilmeEntity.class).addTemplate(FILME_TEMPLATE_VALIDO, new Rule() {{
            add("id", 1L);
            add("nome", "marvel");
            add("atores", has(1).of(AtorEntity.class,AtorEntityTemplate.ATOR_TEMPLATE_VALIDO));
        }});
    }
}

