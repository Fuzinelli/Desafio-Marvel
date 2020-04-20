package br.com.ibm.marvel.templates;

import br.com.ibm.marvel.ator.model.AtorEntity;
import br.com.ibm.marvel.poder.model.PoderEntity;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class PoderEntityTemplate implements TemplateLoader {

    public static final String PODER_TEMPLATE_VALIDO = "poder template valido";

    @Override
    public void load() {
        Fixture.of(PoderEntity.class).addTemplate(PODER_TEMPLATE_VALIDO, new Rule() {{
            add("id", 1L);
            add("descricao", "voar");
        }});
    }
}

