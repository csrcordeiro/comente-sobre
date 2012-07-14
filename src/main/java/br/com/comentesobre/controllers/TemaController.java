package br.com.comentesobre.controllers;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.comentesobre.logica.TemaLogica;
import br.com.comentesobre.model.Tema;

@Resource
public class TemaController {

    private final Result result;

    private final TemaLogica temaLogica;

    public TemaController(Result result, TemaLogica temaLogica) {
        this.result = result;
        this.temaLogica = temaLogica;
    }

    public void escolher(Tema tema) {
        //TODO: Criar validadores.

        temaLogica.escolher(tema);

        result.redirectTo(ComentarioController.class).novoComentario(tema);
    }

}
