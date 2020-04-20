package br.com.ibm.marvel.templates;

import br.com.ibm.marvel.heroi.model.HeroiSaida;
import br.com.ibm.marvel.revista.model.RevistaSaida;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class RevistaSaidaTemplate implements TemplateLoader {
    public static final String REVISTA_SAIDA_VALIDO = "revista saida valido";

    @Override
    public void load() {
        Fixture.of(RevistaSaida.class).addTemplate(REVISTA_SAIDA_VALIDO, new Rule() {{
            add("id", 1L);
            add("nome", "vingadores");
            add("herois", has(1).of(HeroiSaida.class, HeroiSaidaTemplate.HEROI_SAIDA_VALIDO));
        }});
    }
}
