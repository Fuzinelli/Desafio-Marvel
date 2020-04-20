package br.com.ibm.marvel.templates;

import br.com.ibm.marvel.criador.model.CriadorSaida;
import br.com.ibm.marvel.filme.model.FilmeSaida;
import br.com.ibm.marvel.heroi.model.HeroiSaida;
import br.com.ibm.marvel.revista.model.RevistaSaida;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class CriadorSaidaTemplate implements TemplateLoader {
    public static final String CRIADOR_SAIDA_VALIDO = "criador saida valido";
    @Override
    public void load() {
        Fixture.of(CriadorSaida.class).addTemplate(CRIADOR_SAIDA_VALIDO, new Rule() {{
            add("id", 1L);
            add("nome", "gustavo");
            add("filmes", has(1).of(FilmeSaida.class, FilmeSaidaTemplate.FILME_SAIDA_VALIDO));
            add("revistas", has(1).of(RevistaSaida.class, RevistaSaidaTemplate.REVISTA_SAIDA_VALIDO));
            add("herois", has(1).of(HeroiSaida.class, HeroiSaidaTemplate.HEROI_SAIDA_VALIDO));
        }});
    }
}
