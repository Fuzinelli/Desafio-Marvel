package br.com.ibm.marvel.templates;

import br.com.ibm.marvel.poder.model.PoderSaida;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class PoderSaidaTemplate implements TemplateLoader {
    public static final String PODER_SAIDA_VALIDO = "poder saida valido";
    @Override
    public void load() {
        Fixture.of(PoderSaida.class).addTemplate(PODER_SAIDA_VALIDO, new Rule(){{
            add("id", 1L);
            add("descricao", "voar");
        }});
    }
}
