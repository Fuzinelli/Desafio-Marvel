package br.com.ibm.marvel.templates;

import br.com.ibm.marvel.ator.model.AtorSaida;
import br.com.ibm.marvel.heroi.model.HeroiSaida;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class AtorSaidaTemplate implements TemplateLoader {
    public static final String ATOR_SAIDA_VALIDO = "ator saida valido";
    @Override
    public void load() {
        Fixture.of(AtorSaida.class).addTemplate(ATOR_SAIDA_VALIDO, new Rule() {{
            add("id", 1L);
            add("nomeAtor", "lucas");
            add("heroi", one(HeroiSaida.class, HeroiSaidaTemplate.HEROI_SAIDA_VALIDO));
        }});
    }
}
