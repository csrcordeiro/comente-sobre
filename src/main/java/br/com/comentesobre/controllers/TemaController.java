package br.com.comentesobre.controllers;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.comentesobre.logica.TemaLogica;
import br.com.comentesobre.model.Tema;

@Resource
public class TemaController {

    private final Result result;

    private final TemaLogica temaLogica;

    private final Validator validador;

    public TemaController(Result result, TemaLogica temaLogica, Validator validator) {
        this.result = result;
        this.temaLogica = temaLogica;
        this.validador = validator;
    }

    public void escolher(Tema tema) {
        if(tema == null || tema.getTitulo() == null || tema.getTitulo().isEmpty()){
            validador.add(
                    new ValidationMessage("Por favor, escolha um tema.", "")
                    );
        }

        validador.onErrorRedirectTo(ComentarioController.class).home();

        temaLogica.escolher(tema);

        result.redirectTo(ComentarioController.class).novoComentario(tema.getUri());
    }

}
