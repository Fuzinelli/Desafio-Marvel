package br.com.ibm.marvel.templates;

import br.com.ibm.marvel.heroi.model.HeroiEntity;
import br.com.ibm.marvel.poder.model.PoderEntrada;
import br.com.ibm.marvel.revista.model.RevistaEntrada;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.util.ArrayList;
import java.util.List;

public class RevistaEntradaTemplate implements TemplateLoader {

    public static final String REVISTA_ENTRADA_VALIDO = "revista entrada valido";

    @Override
    public void load() {
        ArrayList<Long> lista = new ArrayList<Long>(){{add(1L);}};
        Fixture.of(RevistaEntrada.class).addTemplate(REVISTA_ENTRADA_VALIDO, new Rule() {{
            add("nome", "vingadores");
            add("herois",lista);
        }});
    }
}