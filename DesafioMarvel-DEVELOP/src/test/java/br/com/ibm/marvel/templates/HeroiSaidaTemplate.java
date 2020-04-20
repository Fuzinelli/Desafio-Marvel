package br.com.ibm.marvel.templates;

import br.com.ibm.marvel.heroi.model.HeroiSaida;
import br.com.ibm.marvel.poder.model.PoderSaida;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class HeroiSaidaTemplate implements TemplateLoader {
    public static final String HEROI_SAIDA_VALIDO = "heroi saida valido";
    @Override
    public void load() {
        Fixture.of(HeroiSaida.class).addTemplate(HEROI_SAIDA_VALIDO, new Rule() {{
            add("id", 1L);
            add("nome", "homem de ferro");
            add("poderes", has(1).of(PoderSaida.class, PoderSaidaTemplate.PODER_SAIDA_VALIDO));
        }});
    }
}
