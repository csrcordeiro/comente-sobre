package br.com.comentesobre.controllers;


import java.util.List;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.comentesobre.interceptadores.Restrito;
import br.com.comentesobre.logica.ComentarioLogica;
import br.com.comentesobre.model.Comentario;
import br.com.comentesobre.model.Tema;
import br.com.comentesobre.model.Usuario;

@Resource
public class ComentarioController {

    private final Result result;
    private final ComentarioLogica comentarioLogica;
    private final Validator validador;

    public ComentarioController(Result result, ComentarioLogica comentarioLogica, Validator validador) {
        this.result = result;
        this.comentarioLogica = comentarioLogica;
        this.validador = validador;
    }

    @Path("/")
    public void home() {
    }

    @Path("/{uri}/")
    @Restrito
    public Tema novoComentario(String uri) {
        return comentarioLogica.getTemaAtualParaNovoComentario();
    }

    @Path("/{tema.uri}/comentar")
    @Restrito
    public void comentar(Usuario usuario, Comentario comentario) {
        if(usuario == null || usuario.getEmail() == null || usuario.getEmail().isEmpty()){
            validador.add(
                        new ValidationMessage("Por favor, forneça um email.","")
                    );
        }
        if(comentario == null || comentario.getConteudo() == null || comentario.getConteudo().isEmpty()){
            validador.add(
                        new ValidationMessage("Seu comentario deve ter ao menos uma letra.", "")
                    );
        }

        validador.onErrorRedirectTo(this).novoComentario(comentarioLogica.getTemaAtualParaNovoComentario().getUri());

        comentarioLogica.comentar(usuario, comentario);

        result.redirectTo(this).listar();
    }

    @Path("/lista")
    @Restrito
    public List<Comentario> listar() {
        return comentarioLogica.getComentariosDeTemaEscolhido();
    }
}