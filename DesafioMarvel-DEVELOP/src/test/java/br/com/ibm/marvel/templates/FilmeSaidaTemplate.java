package br.com.ibm.marvel.templates;

import br.com.ibm.marvel.ator.model.AtorSaida;
import br.com.ibm.marvel.filme.model.FilmeSaida;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class FilmeSaidaTemplate implements TemplateLoader {
    public static final String FILME_SAIDA_VALIDO = "filme saida valido";
    @Override
    public void load() {
        Fixture.of(FilmeSaida.class).addTemplate(FILME_SAIDA_VALIDO, new Rule() {{
            add("id", 1L);
            add("nome", "marvel");
            add("atores", has(1).of(AtorSaida.class, AtorSaidaTemplate.ATOR_SAIDA_VALIDO));
        }});
    }
}
