package br.com.ibm.marvel.templates;

import br.com.ibm.marvel.ator.model.AtorEntity;
import br.com.ibm.marvel.heroi.model.HeroiEntity;
import br.com.ibm.marvel.poder.model.PoderEntity;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class HeroiEntityTemplate implements TemplateLoader {
    public static final String HEROI_TEMPLATE_VALIDO = "heroi template valido";

    @Override
    public void load() {
        Fixture.of(HeroiEntity.class).addTemplate(HEROI_TEMPLATE_VALIDO, new Rule() {{
            add("id", 1L);
            add("nome", "homem de ferro");
            add("poderes", has(1).of(PoderEntity.class,PoderEntityTemplate.PODER_TEMPLATE_VALIDO));
        }});
    }
}


