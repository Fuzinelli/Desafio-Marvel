package br.com.ibm.marvel.templates;

import br.com.ibm.marvel.ator.model.AtorEntity;
import br.com.ibm.marvel.heroi.model.HeroiEntity;
import br.com.ibm.marvel.revista.model.RevistaEntity;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class RevistaEntityTemplate  implements TemplateLoader {

    public static final String REVISTA_TEMPLATE_VALIDO = "revista template valido";

    @Override
    public void load() {
        Fixture.of(RevistaEntity.class).addTemplate(REVISTA_TEMPLATE_VALIDO, new Rule() {{
            add("id", 1L);
            add("nome", "vingadores");
            add("herois", has(1).of(HeroiEntity.class,HeroiEntityTemplate.HEROI_TEMPLATE_VALIDO));
        }});
    }
}
