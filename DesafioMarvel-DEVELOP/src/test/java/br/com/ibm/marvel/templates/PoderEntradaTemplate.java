package br.com.ibm.marvel.templates;

import br.com.ibm.marvel.poder.model.PoderEntity;
import br.com.ibm.marvel.poder.model.PoderEntrada;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class PoderEntradaTemplate implements TemplateLoader {

    public static final String PODER_ENTRADA_VALIDO = "poder entrada valido";

    @Override
    public void load() {
        Fixture.of(PoderEntrada.class).addTemplate(PODER_ENTRADA_VALIDO, new Rule() {{
            add("descricao", "voar");
        }});
    }
}

