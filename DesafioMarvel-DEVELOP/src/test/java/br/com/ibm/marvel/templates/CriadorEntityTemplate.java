package br.com.ibm.marvel.templates;

import br.com.ibm.marvel.criador.model.CriadorEntity;
import br.com.ibm.marvel.filme.model.FilmeEntity;
import br.com.ibm.marvel.heroi.model.HeroiEntity;
import br.com.ibm.marvel.revista.model.RevistaEntity;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class CriadorEntityTemplate implements TemplateLoader {
    public static final String CRIADOR_ENTITY_VALIDO = "criador entity valido";
    @Override
    public void load() {
        Fixture.of(CriadorEntity.class).addTemplate(CRIADOR_ENTITY_VALIDO, new Rule(){{
            add("id", 1L);
            add("nome", "gustavo");
            add("herois", has(1).of(HeroiEntity.class, HeroiEntityTemplate.HEROI_TEMPLATE_VALIDO));
            add("revistas", has(1).of(RevistaEntity.class, RevistaEntityTemplate.REVISTA_TEMPLATE_VALIDO));
            add("filmes", has(1).of(FilmeEntity.class, FilmeEntityTemplate.FILME_TEMPLATE_VALIDO));

        }});
    }
}
