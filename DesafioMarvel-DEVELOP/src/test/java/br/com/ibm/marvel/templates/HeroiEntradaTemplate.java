package br.com.ibm.marvel.templates;

import br.com.ibm.marvel.heroi.model.HeroiEntrada;
import br.com.ibm.marvel.poder.model.PoderEntrada;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.awt.*;
import java.util.ArrayList;

public class HeroiEntradaTemplate  implements TemplateLoader {

    public static final String HEROI_ENTRADA_VALIDO = "heroi entrada valido";

    @Override
    public void load() {
        ArrayList<Long> poderes = new ArrayList<>();
        poderes.add(1L);
        Fixture.of(HeroiEntrada.class).addTemplate(HEROI_ENTRADA_VALIDO, new Rule() {{
            add("nome", "homem de ferro");
            add("poderes", poderes);
        }});

    }
}
